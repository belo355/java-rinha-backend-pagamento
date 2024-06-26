package com.rinhabackend.pagamento.infra;

import com.rinhabackend.pagamento.domain.limite.LimiteUseCase;
import com.rinhabackend.pagamento.domain.extrato.ExtratoDTO;
import com.rinhabackend.pagamento.domain.limite.LimiteDTO;
import com.rinhabackend.pagamento.domain.transacao.TransacaoDTO;
import com.rinhabackend.pagamento.domain.transacao.TransacaoUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransacaoController {

    private final TransacaoUseCase transacaoUseCase;
    private final LimiteUseCase limiteUseCase;

    @Autowired
    public TransacaoController(TransacaoUseCase transacaoUseCase, LimiteUseCase limiteUseCase) {
        this.transacaoUseCase = transacaoUseCase;
        this.limiteUseCase = limiteUseCase;
    }

    @PostMapping(path = "/clientes/{id}/transacoes")
    public ResponseEntity createTransacao(@RequestBody @Valid TransacaoDTO transacaoDTO, @PathVariable("id") Long id) {
        LimiteDTO limiteDTO = transacaoUseCase.create(transacaoDTO, id);
        if (isClienteSemLimiteOuSaldo(limiteDTO)) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok().body(limiteDTO);
    }

    private static boolean isClienteSemLimiteOuSaldo(LimiteDTO limiteDTO) {
        return limiteDTO.getSaldo() == 0 && limiteDTO.getLimite() == 0;
    }

    @GetMapping(path = "/clientes/{id}/extrato")
    public ResponseEntity extrato(@PathVariable("id") Long id) {
        ExtratoDTO extratoDTO = transacaoUseCase.extrato(id);
        if (extratoDTO.getUltimasTransacoes().getList().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(extratoDTO);
        }
    }

    //TODO: CONTINUAR A PARTIR DO CADASTRO DO USUARIO

}
