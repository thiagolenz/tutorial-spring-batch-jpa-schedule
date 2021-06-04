package org.exemplo.batchdemo.pessoa.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, Long> {
    Page<Pessoa> findByProcessadoIsFalse(Pageable var1);
}
