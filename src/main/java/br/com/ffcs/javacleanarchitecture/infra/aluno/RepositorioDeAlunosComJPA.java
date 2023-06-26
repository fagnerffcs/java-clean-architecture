package br.com.ffcs.javacleanarchitecture.infra.aluno;

import br.com.ffcs.javacleanarchitecture.dominio.aluno.Aluno;
import br.com.ffcs.javacleanarchitecture.dominio.aluno.CPF;
import br.com.ffcs.javacleanarchitecture.dominio.aluno.RepositorioDeAlunos;

import java.util.List;

public class RepositorioDeAlunosComJPA implements RepositorioDeAlunos {
    @Override
    public void matricular(Aluno aluno) {

    }

    @Override
    public Aluno buscarPorCPF(CPF cpf) throws AlunoNaoEncontradoException {
        return null;
    }

    @Override
    public List<Aluno> listarTodosAlunosMatriculados() {
        return null;
    }
}
