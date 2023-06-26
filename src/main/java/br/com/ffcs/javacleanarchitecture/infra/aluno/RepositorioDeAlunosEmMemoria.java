package br.com.ffcs.javacleanarchitecture.infra.aluno;

import br.com.ffcs.javacleanarchitecture.dominio.aluno.Aluno;
import br.com.ffcs.javacleanarchitecture.dominio.aluno.AlunoNaoEncontradoException;
import br.com.ffcs.javacleanarchitecture.dominio.aluno.CPF;
import br.com.ffcs.javacleanarchitecture.dominio.aluno.RepositorioDeAlunos;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeAlunosEmMemoria implements RepositorioDeAlunos {
    private List<Aluno> matriculados = new ArrayList<>();

    @Override
    public void matricular(Aluno aluno) {
        this.matriculados.add(aluno);
    }

    @Override
    public Aluno buscarPorCPF(CPF cpf) throws AlunoNaoEncontradoException {
        for (Aluno a : matriculados) {
            if(a.getCpf().getNumero().equals(cpf.getNumero())) {
                return a;
            }
        }
        throw new AlunoNaoEncontradoException(cpf);
    }

    @Override
    public List<Aluno> listarTodosAlunosMatriculados() {
        return this.matriculados;
    }
}
