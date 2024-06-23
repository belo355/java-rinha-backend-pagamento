package com.rinhabackend.pagamento.infra.repo;

import com.rinhabackend.pagamento.domain.limite.LimiteDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimiteRepository extends CrudRepository<LimiteDB, Long> {
}
