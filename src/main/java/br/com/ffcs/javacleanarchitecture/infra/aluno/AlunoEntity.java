package br.com.ffcs.javacleanarchitecture.infra.aluno;

import br.com.ffcs.javacleanarchitecture.dominio.aluno.Aluno;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AlunoEntity {

    @Id
    private Long id;

    private String nome;
    private String cpf;
    private String email;

}
