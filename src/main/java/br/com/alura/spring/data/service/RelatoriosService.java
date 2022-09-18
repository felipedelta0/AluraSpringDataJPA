package br.com.alura.spring.data.service;

import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private final FuncionarioRepository funcionarioRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner sc) {
        while (true) {
            System.out.println("\n[RELATORIO] Qual ação deseja executar?");
            System.out.println("1 - Busca Funcionario por Nome");
            System.out.println("2 - Busca Funcionario por Nome, Data de Contratação e Salário");
            System.out.println("3 - Busca Funcionario Data de Contratação Maior ou Igual");
            System.out.println("0 - Sair");

            int action = sc.nextInt();

            switch (action) {
                case 1:
                    buscarFuncionarioPorNome(sc);
                    break;

                case 2:
                    buscarPorNomeSalarioMaiorDataContratacao(sc);
                    break;

                case 3:
                    buscaFuncionarioDataContratacao(sc);
                    break;

                default:
                    return;
            }
        }
    }

    private void buscarFuncionarioPorNome(Scanner sc) {
        System.out.println("Informe o nome a buscar: ");
        String nome = sc.next();
        funcionarioRepository.findByNome(nome)
                .forEach(System.out::println);
    }

    private void buscarPorNomeSalarioMaiorDataContratacao(Scanner sc) {
        System.out.println("Informe o nome a buscar: ");
        String nome = sc.next();

        System.out.println("Informe a data de contratação a buscar: ");
        LocalDate data = LocalDate.parse(sc.next(), formatter);

        System.out.println("Informe o salário a buscar: ");
        BigDecimal salario = sc.nextBigDecimal();

        funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, data)
                .forEach(System.out::println);
    }

    private void buscaFuncionarioDataContratacao(Scanner sc) {
        System.out.println("Informe a data de contratação a buscar: ");
        LocalDate data = LocalDate.parse(sc.next(), formatter);

        funcionarioRepository.findDataContratacaoMaiorQue(data)
                .forEach(System.out::println);
    }
}
