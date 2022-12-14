package br.com.alura.spring.data.repository;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>,
        JpaSpecificationExecutor<Funcionario> {

    List<Funcionario> findByNome(String nome);

    @Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.dataContratacao = :data AND f.salario >= :salario")
    List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, BigDecimal salario, LocalDate data);

    @Query(value = "SELECT * FROM funcionarios WHERE data_contratacao >= :data",
            nativeQuery = true)
    List<Funcionario> findDataContratacaoMaiorQue(LocalDate data);

    @Query(value = "SELECT id, nome, salario FROM funcionarios",
            nativeQuery = true)
    List<FuncionarioProjecao> findFuncionarioSalario();
}
