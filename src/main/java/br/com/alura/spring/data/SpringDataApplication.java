package br.com.alura.spring.data;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.service.CrudCargoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CrudCargoService crudCargoService;

	public SpringDataApplication(CrudCargoService crudCargoService) {
		this.crudCargoService = crudCargoService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("\nQual ação deseja executar?");
			System.out.println("1 - Cargo");
			System.out.println("0 - Sair");

			int action = sc.nextInt();

			if (action == 1) {
				crudCargoService.inicial(sc);
			} else {
				break;
			}
		}

		System.out.println("\nEncerrando aplicação...\n");
	}
}
