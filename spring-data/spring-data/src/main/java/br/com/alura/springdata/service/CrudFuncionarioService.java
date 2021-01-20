package br.com.alura.springdata.service;

import br.com.alura.springdata.orm.Cargo;
import br.com.alura.springdata.orm.Funcionario;
import br.com.alura.springdata.orm.FuncionarioDto;
import br.com.alura.springdata.orm.Unidade;
import br.com.alura.springdata.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private CrudCargoService cargoService;
    @Autowired
    private CrudUnidadeService unidadeService;

    private boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void inicial(Scanner scanner){
        while (system){
            System.out.println("Qual ação sobre Funcionário deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Listar");
            System.out.println("4 - Deletar");
            System.out.println("5 - funcionario salário");

            switch (scanner.nextInt()){
                case 1:
                    salvar(scanner);
                    break;
//                case 2:
//                    atualizar(scanner);
//                    break;
                case 3:
                    mostrarLista(scanner);
                    break;
                case 4:
                    deletar(scanner);
                    break;
                case 5:
                    funcionarioSalario();
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void salvar(Scanner scanner){
        boolean aux = true;

        Funcionario funcionario = new Funcionario();

        System.out.println("Nome do funcionário: ");
        funcionario.setNome(scanner.next());

        System.out.println("CPF: ");
        funcionario.setCpf(scanner.next());

        System.out.println("Salário: ");
        funcionario.setSalario(scanner.nextDouble());

        System.out.println("Data de contratação: ");
        String data = scanner.next();
        LocalDateTime localDateTime = LocalDate.parse(data, formatter).atStartOfDay();
        funcionario.setDataContratacao(localDateTime);

        //Cargo
        System.out.println("Deseja ver opções para cargos?");
        System.out.println("1 - Sim");
        System.out.println("2 - Não");

        if (scanner.nextInt() == 1) {
            cargoService.Inicial(scanner);
        }

        System.out.println("Id do cargo: ");
        Cargo cargo = cargoService.consultarPorId(scanner.nextInt());

        funcionario.setCargo(cargo);
        //Unidade
        System.out.println("Deseja ver opções para unidades?");
        System.out.println("1 - Sim");
        System.out.println("2 - Não");

        if (scanner.nextInt() == 1) {
            unidadeService.Inicial(scanner);
        }

        List<Unidade> unidades = new ArrayList<Unidade>();

        while (aux){
            System.out.println("Deseja cadastrar unidade? ");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");

            if (scanner.nextInt() == 1){
                System.out.println("Id: ");
                Unidade unidade = unidadeService.consultarPorId(scanner.nextInt());

                unidades.add(unidade);
            } else {
                aux = false;
            }
        }
        funcionario.setUnidades(unidades);
        funcionarioRepository.save(funcionario);
    }

    public void mostrarLista(Scanner scanner){
        System.out.println("Qual página deseja visualizar?");
        Integer page = scanner.nextInt();

        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "nome"));

        Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
        System.out.println(funcionarios);
        System.out.println("Página atual: " + funcionarios.getNumber());
        System.out.println("Total de elementos: " + funcionarios.getTotalElements());
        System.out.println("Funcionários: ");
        funcionarios.forEach(funcionario -> System.out.println(funcionario.toString()));
    }

    public void deletar(Scanner scanner){
        System.out.println("Id: ");
        funcionarioRepository.deleteById(scanner.nextInt());
    }

    public void funcionarioSalario(){
        List<FuncionarioDto> list = funcionarioRepository.findFuncionarioSalario();
        list.forEach(f -> System.out.println("Funcionário: id: " + f.getId() +
                " | nome" + f.getNome() + " | salário: " + f.getSalario()));
    }
}
