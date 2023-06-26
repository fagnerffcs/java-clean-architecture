package br.com.ffcs.javacleanarchitecture;

import br.com.ffcs.javacleanarchitecture.aplicacao.aluno.MatricularAluno;
import br.com.ffcs.javacleanarchitecture.aplicacao.aluno.MatricularAlunoDto;
import br.com.ffcs.javacleanarchitecture.dominio.aluno.Aluno;
import br.com.ffcs.javacleanarchitecture.dominio.aluno.CPF;
import br.com.ffcs.javacleanarchitecture.infra.aluno.RepositorioDeAlunosEmMemoria;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatricularAlunoTest {
    @Test
    void alunoDeveriaSerPersistido(){
        RepositorioDeAlunosEmMemoria repositorio = new RepositorioDeAlunosEmMemoria();
        MatricularAluno useCase = new MatricularAluno(repositorio);
        MatricularAlunoDto matricularAlunoDto = new MatricularAlunoDto("Fulano", "111.111.111-11", "fulano@email.com");
        useCase.executa(matricularAlunoDto);

        Aluno encontrado = repositorio.buscarPorCPF(CPF.builder().numero("111.111.111-11").build());
        assertEquals("Fulano", encontrado.getNome());
        assertEquals("111.111.111-11", encontrado.getCpf().getNumero());
        assertEquals("fulano@email.com", encontrado.getEmail().getEndereco());
    }
}
