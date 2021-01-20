package br.com.alura.springdata.service;

import br.com.alura.springdata.orm.Cargo;
import br.com.alura.springdata.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudCargoService {

    private final CargoRepository cargoRepository;
    private boolean system = true;

    public CrudCargoService(CargoRepository cargoRepository){
        this.cargoRepository = cargoRepository;
    }

    public void Inicial(Scanner scanner){
        while (system){
            System.out.println("Qual ação sobre cargo deseja executar?");
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
                case 4:
                    deletar(scanner);
                    break;
                case 3:
                    listaCompleta();
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    public void Salvar(Scanner scanner){
        System.out.println("Descrição do cargo: ");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);

        cargoRepository.save(cargo);
    }

    public void listaCompleta(){
        Iterable<Cargo> cargos = cargoRepository.findAll();
        System.out.println("Cargos: ");
        cargos.forEach(cargo -> System.out.println(cargo.toString()));
    }

    public void atualizar(Scanner scanner){
        System.out.println("Id: ");
        int id = scanner.nextInt();
        System.out.println("Nova descrição: ");
        String descricao = scanner.next();

        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setDescricao(descricao);

        cargoRepository.save(cargo);
    }

    public void deletar(Scanner scanner){
        listaCompleta();
        System.out.println("ID: ");
        cargoRepository.deleteById(scanner.nextInt());
    }

    public Cargo consultarPorId(Integer id){
        Optional<Cargo> cargo = cargoRepository.findById(id);
        return cargo.orElse(null);
    }
}
