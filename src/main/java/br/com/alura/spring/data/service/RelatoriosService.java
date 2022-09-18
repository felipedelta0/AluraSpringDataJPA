package br.com.alura.spring.data.service;

import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class RelatoriosService {

    private final FuncionarioRepository funcionarioRepository;

    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner sc) {
        while (true) {
            System.out.println("\n[RELATORIO] Qual ação deseja executar?");
            System.out.println("1 - Busca Funcionario por Nome");
            System.out.println("0 - Sair");

            int action = sc.nextInt();

            switch (action) {
                case 1:
                    buscarFuncionarioPorNome(sc);
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
}
