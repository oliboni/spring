package br.com.alura.forum.repository;

import br.com.alura.forum.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    List<Topico> findByCursoNome(String nomeCurso);
    //Caso exista ambiguidade no nome pode usar assim para pegar dentro do relacimanto curso
    //List<Topico> findByCurso_Nome(String nomeCurso);

    //Metodo manual
    @Query("SELECT t FROM Topico t where t.curso.nome = :nomeCurso")
    List<Topico> carregarPorNomeCurso(@Param("nomeCurso") String nomeCurso);
}
