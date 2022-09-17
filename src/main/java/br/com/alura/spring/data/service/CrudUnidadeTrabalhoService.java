package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudUnidadeTrabalhoService {

    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    public void inicial(Scanner sc) {

        while (true) {
            System.out.println("\n[UNIDADE DE TRABALHO] Qual ação deseja executar?");
            System.out.println("1 - Listar");
            System.out.println("2 - Novo");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Sair");

            int action = sc.nextInt();

            switch (action) {
                case 1:
                    listar();
                    break;

                case 2:
                    salvar(sc);
                    break;

                case 3:
                    atualizar(sc);
                    break;

                case 4:
                    excluir(sc);
                    break;

                default:
                    return;
            }
        }
    }

    private void listar() {
        unidadeTrabalhoRepository
                .findAll()
                .forEach(System.out::println);
    }

    private void salvar(Scanner sc) {
        System.out.println("Nome da Unidade de Trabalho: ");
        String nome = sc.next();

        System.out.println("Endereço Unidade de Trabalho: ");
        String endereco = sc.next();

        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setDescricao(nome);
        unidadeTrabalho.setEndereco(endereco);
        unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Unidade de Trabalho salva!");
    }

    private void atualizar(Scanner sc) {
        System.out.println("ID da unidade de trabalho para alterar: ");
        int id = sc.nextInt();

        System.out.println("Insira o novo nome:");
        String nome = sc.next();

        System.out.println("Insira o novo endereço:");
        String endereco = sc.next();

        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setId(id);
        unidadeTrabalho.setDescricao(nome);
        unidadeTrabalho.setEndereco(endereco);

        unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Unidade de Trabalho atualizada!");
    }

    private void excluir(Scanner sc) {
        System.out.println("ID da unidade de trabalho para excluir: ");
        int id = sc.nextInt();

        unidadeTrabalhoRepository.deleteById(id);
        System.out.println("Unidade de Trabalho excluída");
    }
}
