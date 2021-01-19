package br.com.alura.forum.repository;

import br.com.alura.forum.modelo.Curso;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//Para configurar um outro banco para executar os testes
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//Ativa um profile
@ActiveProfiles("test")
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository repository;
    @Autowired
    private TestEntityManager em;

    @Test
    public void deveriaCarregarUmCursoAoBuscarPeloSeuNome(){
        Curso curso1 = new Curso();

        String nomeCurso = "Spring Boot";
        curso1.setNome(nomeCurso);
        curso1.setCategoria("Programação");

        em.persist(curso1);

        Curso curso = repository.findByNome(nomeCurso);

        assertThat(curso).isNotNull();
        assertThat(nomeCurso).isEqualTo(curso.getNome());
    }

    @Test
    public void NaoDeveriaCarregarUmCursoAoBuscarPeloSeuNome(){
        String nomeCurso = "aaaaaaaa";
        Curso curso = repository.findByNome(nomeCurso);

        assertThat(curso).isNull();
    }
}
