package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;
    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository,
                                  CargoRepository cargoRepository,
                                  UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    public void inicial(Scanner sc) {
        while (true) {
            System.out.println("\n[FUNCIONARIO] Qual ação deseja executar?");
            System.out.println("1 - Listar");
            System.out.println("2 - Novo");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Sair");

            int action = sc.nextInt();

            switch (action) {
                case 1:
                    listar(sc);
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

    private void listar(Scanner sc) {
        System.out.println("Qual página deseja visualizar: ");
        Integer page = sc.nextInt();

        Pageable pageable = PageRequest.of(page, 4, Sort.by(Sort.Direction.ASC, "nome")
                .and(Sort.by(Sort.Direction.ASC, "salario")));
        Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);

        System.out.println("\n");
        System.out.println(funcionarios);
        System.out.println("Página atual: " + funcionarios.getNumber());
        System.out.println("Total elementos: " + funcionarios.getTotalElements());
        funcionarios.forEach(System.out::println);
    }

    private void salvar(Scanner sc) {
        System.out.println("Nome do Funcionario: ");
        String nome = sc.next();

        System.out.println("CPF do Funcionario: ");
        String cpf = sc.next();

        System.out.println("Salário do Funcionario: ");
        BigDecimal salario = sc.nextBigDecimal();

        System.out.println("Data de Contratação: ");
        String data = sc.next();

        System.out.println("Novo ID do cargo: ");
        Integer cargoId = sc.nextInt();

        List<UnidadeTrabalho> unidades = unidades(sc);

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(data, formatter));
        funcionario.setCargo(cargoRepository.findById(cargoId).orElse(null));
        funcionario.setUnidadeTrabalhos(unidades);

        funcionarioRepository.save(funcionario);
        System.out.println("Funcionário cadastrado!");
    }

    private List<UnidadeTrabalho> unidades(Scanner sc) {
        List<UnidadeTrabalho> unidades = new ArrayList<>();

        while (true) {
            System.out.println("Digite o unidadeID (para sair digite 0): ");
            int unidadeId = sc.nextInt();

            if (unidadeId == 0)
                break;

            unidades.add(unidadeTrabalhoRepository.findById(unidadeId).orElse(null));
        }
        return unidades;
    }

    private void atualizar(Scanner sc) {
        System.out.println("ID do funcionário para atualizar: ");
        int id = sc.nextInt();

        System.out.println("Novo nome do Funcionario: ");
        String nome = sc.next();

        System.out.println("Novo CPF do Funcionario: ");
        String cpf = sc.next();

        System.out.println("Novo salário do Funcionario: ");
        BigDecimal salario = sc.nextBigDecimal();

        System.out.println("Nova data de Contratação: ");
        String data = sc.next();

        System.out.println("Novo ID do cargo: ");
        Integer cargoId = sc.nextInt();

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(data, formatter));
        funcionario.setCargo(cargoRepository.findById(cargoId).orElse(null));

        funcionarioRepository.save(funcionario);
        System.out.println("Funcionário atualizado!");
    }

    private void excluir(Scanner sc) {
        System.out.println("ID do funcionário para excluir: ");
        int id = sc.nextInt();

        funcionarioRepository.deleteById(id);
        System.out.println("Funcionário excluído");
    }
}
