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
            System.out.println("[CARGO] Qual ação deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Novo");
            System.out.println("2 - Atualizar");

            int action = sc.nextInt();

            switch (action) {
                case 1:
                    salvar(sc);
                    break;

                case 2:
                    atualizar(sc);
                    break;

                default:
                    return;
            }
        }
    }

    private void salvar(Scanner sc) {
        System.out.println("Descrição do Cargo: ");
        String descricao = sc.next().toUpperCase();
        Cargo cargo = new Cargo(descricao);
        cargoRepository.save(cargo);
        System.out.println("Cargo salvo!");
    }

    private void atualizar(Scanner sc) {
        System.out.println("ID registro para alterar: ");
        int id = sc.nextInt();

        System.out.println("Insira a nova descrição:");
        String descricao = sc.next();

        Cargo cargo = new Cargo(id, descricao);
        cargoRepository.save(cargo);
        System.out.println("Cargo atualizado!");
    }
}
