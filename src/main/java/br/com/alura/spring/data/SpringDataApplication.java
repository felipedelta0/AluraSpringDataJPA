package br.com.alura.spring.data;

import br.com.alura.spring.data.service.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
@AllArgsConstructor
public class SpringDataApplication implements CommandLineRunner {

	private final CrudCargoService crudCargoService;
	private final CrudFuncionarioService crudFuncionarioService;
	private final CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;
	private final RelatoriosService relatoriosService;
	private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		rodar();
		System.out.println("\nEncerrando aplicação...\n");
	}

	public void rodar() {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("\nQual ação deseja executar?");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Unidade Trabalho");
			System.out.println("4 - Relatórios");
			System.out.println("5 - Relatório Dinâmico");
			System.out.println("0 - Sair");

			int action = sc.nextInt();

			switch(action) {
				case 1:
					crudCargoService.inicial(sc);
					break;

				case 2:
					crudFuncionarioService.inicial(sc);
					break;

				case 3:
					crudUnidadeTrabalhoService.inicial(sc);
					break;

				case 4:
					relatoriosService.inicial(sc);
					break;

				case 5:
					relatorioFuncionarioDinamico.inicial(sc);
					break;

				default:
					return;
			}
		}
	}
}
