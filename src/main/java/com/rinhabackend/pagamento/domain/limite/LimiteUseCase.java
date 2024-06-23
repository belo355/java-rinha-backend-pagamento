package com.rinhabackend.pagamento.domain.limite;

import com.rinhabackend.pagamento.infra.repo.LimiteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LimiteUseCase {

    private static final Logger logger = LoggerFactory.getLogger(LimiteUseCase.class);
    private final LimiteRepository limiteRepository;

    @Autowired
    public LimiteUseCase(LimiteRepository limiteRepository) {
        this.limiteRepository = limiteRepository;
    }

    public boolean cadastraLimiteInicial(Long id, int limite) {
        LimiteDB limiteDB = new LimiteDB(id, limite, 0);
        try{
            limiteRepository.save(limiteDB);
            return true;
        }catch (Exception e){
            logger.error("Erro ao tentar cadastrar o limite para o id: {}", id);
        }
        return false;
    }

    public boolean atualizaSaldo(Long id, int newSaldo) {
        Optional<LimiteDB> limiteDB = limiteRepository.findById(id);
        if (limiteDB.isPresent()) {
            limiteDB.get().setSaldo(newSaldo);
            limiteRepository.save(limiteDB.get());
            return true;
        }
        logger.error("Erro ao tentar atualizado o saldo para o id: {}", id);
        return false;
    }
}

