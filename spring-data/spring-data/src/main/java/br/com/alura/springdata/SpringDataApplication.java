package br.com.alura.springdata;

import br.com.alura.springdata.orm.Cargo;
import br.com.alura.springdata.repository.CargoRepository;
import br.com.alura.springdata.service.CrudCargoService;
import br.com.alura.springdata.service.CrudFuncionarioService;
import br.com.alura.springdata.service.CrudUnidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	@Autowired
	private CrudUnidadeService unidadeService;
	@Autowired
	private CrudFuncionarioService funcionarioService;

	private final CrudCargoService cargoService;
	boolean system = true;

	public SpringDataApplication(CrudCargoService cargoService){
		this.cargoService = cargoService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new  Scanner(System.in);

		while (system){
			System.out.println("Qual ação voçe quer?");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Unidade");
			System.out.println("3 - Funcionário");

			int action = scanner.nextInt();
			switch (action){
				case 1:
					cargoService.Inicial(scanner);
					break;
				case 2:
					unidadeService.Inicial(scanner);
					break;
				case 3:
					funcionarioService.inicial(scanner);
					break;
				default:
					system = false;
					System.out.println("Até mais!!");
			}
		}
	}
}
