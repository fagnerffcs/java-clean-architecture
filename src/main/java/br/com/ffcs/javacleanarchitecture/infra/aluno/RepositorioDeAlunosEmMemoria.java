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
        return matriculados.stream().filter(a -> a.getCpf().equals(cpf.getNumero()))
                                    .findFirst()
                                    .orElseThrow(() -> new AlunoNaoEncontradoException(cpf));
    }

    @Override
    public List<Aluno> listarTodosAlunosMatriculados() {
        return this.matriculados;
    }
}
