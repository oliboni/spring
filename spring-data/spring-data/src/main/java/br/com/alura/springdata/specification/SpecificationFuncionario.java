package br.com.alura.springdata.specification;


import br.com.alura.springdata.orm.Funcionario;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationFuncionario {
    public static Specification<Funcionario> nome(String nome){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("nome"),"%"+nome+"%"));
    }
}
