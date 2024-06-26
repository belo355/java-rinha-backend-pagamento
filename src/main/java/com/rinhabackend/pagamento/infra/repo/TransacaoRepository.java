package com.rinhabackend.pagamento.infra.repo;

import com.rinhabackend.pagamento.domain.transacao.TransacaoDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransacaoRepository extends CrudRepository<TransacaoDB, Long> {
    List<Optional<TransacaoDB>> findAllById(Long id);
    List<Optional<TransacaoDB>> findByUserId(Long id);

}
