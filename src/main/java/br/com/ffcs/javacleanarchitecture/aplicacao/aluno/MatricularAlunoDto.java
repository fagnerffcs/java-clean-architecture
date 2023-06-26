package br.com.ffcs.javacleanarchitecture.aplicacao.aluno;

import br.com.ffcs.javacleanarchitecture.dominio.aluno.Aluno;
import br.com.ffcs.javacleanarchitecture.dominio.aluno.CPF;
import br.com.ffcs.javacleanarchitecture.dominio.aluno.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MatricularAlunoDto {
    private String nomeAluno;
    private String cpfAluno;
    private String emailAluno;

    public Aluno criarAluno() {
        return Aluno.builder()
                    .nome(nomeAluno)
                    .cpf(CPF.builder().numero(cpfAluno).build())
                    .email(Email.builder().endereco(emailAluno).build())
                    .build();
    }
}
