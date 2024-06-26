package com.rinhabackend.pagamento.domain.transacao;

import com.rinhabackend.pagamento.domain.extrato.ExtratoDTO;
import com.rinhabackend.pagamento.domain.extrato.SaldoDTO;
import com.rinhabackend.pagamento.domain.extrato.TransacoesDTO;
import com.rinhabackend.pagamento.domain.extrato.UltimasTransacoesDTO;
import com.rinhabackend.pagamento.domain.limite.LimiteDB;
import com.rinhabackend.pagamento.domain.limite.LimiteDTO;
import com.rinhabackend.pagamento.infra.repo.LimiteRepository;
import com.rinhabackend.pagamento.infra.repo.TransacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class TransacaoUseCase {

    private static final Logger logger = LoggerFactory.getLogger(TransacaoUseCase.class);
    private final TransacaoRepository transacaoRepository;
    private final LimiteRepository limiteRepository;

    @Autowired
    public TransacaoUseCase(TransacaoRepository transacaoRepository, LimiteRepository limiteRepository) {
        this.transacaoRepository = transacaoRepository;
        this.limiteRepository = limiteRepository;
    }

    public LimiteDTO create(TransacaoDTO transacaoDTO, Long id) {
        TransacaoDB transacaoDB = new TransacaoDB(transacaoDTO, id);
        try {
            boolean transacaoValida = validAndExectLimiteChange(id, transacaoDTO);
            if(transacaoValida) {
                transacaoRepository.save(transacaoDB);
                logger.info("Transacao com sucesso");
                Optional<LimiteDB> limiteDB = limiteRepository.findById(id);
                if(limiteDB.isPresent()){
                    return new LimiteDTO(limiteDB.get());
                }
            }
        }catch (Exception e ) {
            logger.error("Error ao efetuar transacao");
        }
        return new LimiteDTO(0,0);
    }

    private boolean validAndExectLimiteChange(Long id, TransacaoDTO transacaoDTO) {
        Optional<LimiteDB> limiteDB = limiteRepository.findById(id);
        if(limiteDB.isPresent()) {
            LimiteDB limiteDB1 = limiteDB.get();
            if (Objects.equals(transacaoDTO.getTipo(), "c")) {
                limiteDB1.updateCredSaldo(transacaoDTO.getValor());
                limiteRepository.save(limiteDB1);
                return true;
            } else { //debit
                boolean saldoValid = limiteDB1.validDebitSaldo(transacaoDTO.getValor());
                if (saldoValid) {
                    limiteDB1.updateDebitSaldo(transacaoDTO.getValor());
                    limiteRepository.save(limiteDB1);
                    return true;
                } else {
                    logger.info("saldo nao deve ser menor que o limite");
                    return false;
                }
            }
        }
        logger.info("Não ha limite cadastrado para o cliente");
        return false;
    }

    public ExtratoDTO extrato(Long id) {
        Optional<LimiteDB> limiteDB = limiteRepository.findById(id);
        List<Optional<TransacaoDB>> transacaoDB = transacaoRepository.findByUserId(id);
        SaldoDTO saldoDto = new SaldoDTO();
        UltimasTransacoesDTO ultimasTransacoesDTO = new UltimasTransacoesDTO();

        if(limiteDB.isPresent()) {
            saldoDto.setDataExtrato(new Date());
            saldoDto.setLimite(limiteDB.get().getLimite());
            saldoDto.setTotal(limiteDB.get().getSaldo());
        }

        if(transacaoDB.size() == 0) {
            logger.info("Não transacoes para o cliente");
            ultimasTransacoesDTO.setList(new ArrayList<>());
        }else {
            List<TransacoesDTO> list = new ArrayList<>();
            transacaoDB.forEach(t -> list.add(
                    new TransacoesDTO(
                            t.get().getValor(),
                            t.get().getTipo(),
                            t.get().getDescricao(),
                            t.get().getRealizada_em()
                    )));

            //ordenando lista e filtrando ultimas 10 transacoes
            List<TransacoesDTO> sortedList = list.stream()
                    .sorted(Comparator.comparing(TransacoesDTO::getRealizadaEm).reversed())
                    .limit(10)
                    .collect(Collectors.toList());

            ultimasTransacoesDTO.setList(sortedList);
        }
        ExtratoDTO extratoDTO = new ExtratoDTO();
        extratoDTO.setSaldoDto(saldoDto);
        extratoDTO.setUltimasTransacoes(ultimasTransacoesDTO);
        return extratoDTO;
    }
}
