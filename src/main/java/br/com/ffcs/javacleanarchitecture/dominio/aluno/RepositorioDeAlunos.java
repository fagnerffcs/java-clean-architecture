package br.com.ffcs.javacleanarchitecture.dominio.aluno;

import java.util.List;

public interface RepositorioDeAlunos {
    void matricular(Aluno aluno);
    Aluno buscarPorCPF(CPF cpf) throws AlunoNaoEncontradoException;
    List<Aluno> listarTodosAlunosMatriculados();
}
