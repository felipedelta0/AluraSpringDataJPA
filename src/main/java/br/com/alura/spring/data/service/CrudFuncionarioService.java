package br.com.alura.spring.data.service;

import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudFuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner sc) {
        while (true) {
            System.out.println("\n[CARGO] Qual ação deseja executar?");
            System.out.println("1 - Listar");
            System.out.println("2 - Novo");
//            System.out.println("3 - Atualizar");
//            System.out.println("4 - Excluir");
            System.out.println("0 - Sair");

            int action = sc.nextInt();

            switch (action) {
                case 1:
                    listar();
                    break;

                default:
                    return;
            }
        }
    }

    private void listar() {
        funcionarioRepository
                .findAll()
                .forEach(System.out::println);
    }

    private void salvar(Scanner sc) {
        System.out.println("Nome do Funcionario: ");
        String nome = sc.nextLine();
        System.out.println("CPF do Funcionario: ");
        System.out.println("Salário do Funcionario: ");
        System.out.println("Data de Contratação: ");
    }
}
