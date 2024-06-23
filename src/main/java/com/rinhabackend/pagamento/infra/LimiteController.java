package com.rinhabackend.pagamento.infra;

import com.rinhabackend.pagamento.domain.limite.LimiteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LimiteController {

    private final LimiteUseCase limiteUseCase;

    @Autowired
    public LimiteController(LimiteUseCase limiteUseCase) {
        this.limiteUseCase = limiteUseCase;
    }

    @PostMapping(path = "/limite/{id}/{newLimite}")
    public ResponseEntity cadastrarLimiteInicial(@PathVariable("id") Long id, @PathVariable("newLimite") int newLimite) {
        boolean limiteCadastrado = limiteUseCase.cadastraLimiteInicial(id, newLimite);
        if (limiteCadastrado) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/limite/saldo/{id}/{newSaldo}")
    public ResponseEntity updateSaldo(@PathVariable("id") Long id, @PathVariable("newSaldo") int newSaldo) {
        boolean limiteCadastrado = limiteUseCase.atualizaSaldo(id, newSaldo);
        if (limiteCadastrado) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
