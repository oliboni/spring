package br.com.alura.springdata.repository;

import br.com.alura.springdata.orm.Funcionario;
import br.com.alura.springdata.orm.FuncionarioDto;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>, JpaSpecificationExecutor<Funcionario> {
        @Query(value = "SELECT f.id, f.nome, f.salario from funcionarios f", nativeQuery = true)
        List<FuncionarioDto> findFuncionarioSalario();

}
