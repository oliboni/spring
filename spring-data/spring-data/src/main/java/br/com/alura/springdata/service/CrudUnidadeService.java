package br.com.alura.springdata.service;

import br.com.alura.springdata.orm.Unidade;
import br.com.alura.springdata.repository.UnidadeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudUnidadeService {

    private final UnidadeRepository unidadeRepository;
    private boolean system = true;

    public CrudUnidadeService(UnidadeRepository unidadeRepository){
        this.unidadeRepository = unidadeRepository;
    }

    public void Inicial(Scanner scanner){
        while (system){
            System.out.println("Qual ação sobre unidade deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Listar");
            System.out.println("4 - Deletar");

            switch (scanner.nextInt()){
                case 1:
                    Salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    listaCompleta();
                    break;
                case 4:
                    deletar(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    public void Salvar(Scanner scanner){
        Unidade unidade = new Unidade();

        System.out.println("Descrição do unidade: ");
        String descricao = scanner.next();
        unidade.setDescricao(descricao);

        System.out.println("Descrição do endereço: ");
        unidade.setEndereco(scanner.next());

        unidadeRepository.save(unidade);
    }

    public void listaCompleta(){
        Iterable<Unidade> unidades = unidadeRepository.findAll();
        System.out.println("Unidades: ");
        unidades.forEach(unidade -> System.out.println(unidade.toString()));
    }

    public void atualizar(Scanner scanner){
        System.out.println("Id: ");
        int id = scanner.nextInt();
        System.out.println("Nova descrição: ");
        String descricao = scanner.next();
        System.out.println("Novo Endereço: ");
        String endereco = scanner.next();


        Unidade unidade = new Unidade();
        unidade.setId(id);
        unidade.setDescricao(descricao);
        unidade.setEndereco(endereco);

        unidadeRepository.save(unidade);
    }

    public void deletar(Scanner scanner){
        listaCompleta();
        System.out.println("ID: ");
        unidadeRepository.deleteById(scanner.nextInt());
    }

    public Unidade consultarPorId(Integer id){
        Optional<Unidade> unidade = unidadeRepository.findById(id);
        return unidade.orElse(null);
    }
}
