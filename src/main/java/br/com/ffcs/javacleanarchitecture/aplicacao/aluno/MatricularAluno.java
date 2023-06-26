package br.com.ffcs.javacleanarchitecture.aplicacao.aluno;

import br.com.ffcs.javacleanarchitecture.dominio.aluno.Aluno;
import br.com.ffcs.javacleanarchitecture.dominio.aluno.RepositorioDeAlunos;

public class MatricularAluno {
    private RepositorioDeAlunos repositorioDeAlunos;

    public MatricularAluno(RepositorioDeAlunos repositorioDeAlunos){
        this.repositorioDeAlunos = repositorioDeAlunos;
    }

    public void executa(MatricularAlunoDto dados) {
        Aluno novo = dados.criarAluno();
        repositorioDeAlunos.matricular(novo);
    }
}
