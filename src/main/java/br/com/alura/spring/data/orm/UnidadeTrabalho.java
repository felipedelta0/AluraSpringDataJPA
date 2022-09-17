package br.com.alura.spring.data.orm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "unidade_trabalho")
public class UnidadeTrabalho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    private String endereco;

    @ManyToMany(mappedBy = "unidadeTrabalhos", fetch = FetchType.EAGER)
    private List<Funcionario> funcionarios;

    @Override
    public String toString() {
        return "UnidadeTrabalho{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", endereco='" + endereco + '\'' +
                ", funcionarios=" + funcionarios +
                '}';
    }
}
