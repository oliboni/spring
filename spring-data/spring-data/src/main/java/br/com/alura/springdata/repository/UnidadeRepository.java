package br.com.alura.springdata.repository;

import br.com.alura.springdata.orm.Unidade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeRepository extends CrudRepository<Unidade, Integer> {
}
