package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {

    private final CargoRepository cargoRepository;

    public CrudCargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public void inicial(Scanner sc) {

        while (true) {
            System.out.println("\n[CARGO] Qual ação deseja executar?");
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
        cargoRepository
                .findAll()
                .forEach(System.out::println);
    }

    private void salvar(Scanner sc) {
        System.out.println("Descrição do Cargo: ");
        String descricao = sc.next().toUpperCase();
        Cargo cargo = new Cargo(descricao);
        cargoRepository.save(cargo);
        System.out.println("Cargo salvo!");
    }

    private void atualizar(Scanner sc) {
        System.out.println("ID do registro para alterar: ");
        int id = sc.nextInt();

        System.out.println("Insira a nova descrição:");
        String descricao = sc.next();

        Cargo cargo = new Cargo(id, descricao);
        cargoRepository.save(cargo);
        System.out.println("Cargo atualizado!");
    }

    private void excluir(Scanner sc) {
        System.out.println("ID do registro para excluir: ");
        int id = sc.nextInt();

        cargoRepository.deleteById(id);
        System.out.println("Cargo excluído");
    }
}
