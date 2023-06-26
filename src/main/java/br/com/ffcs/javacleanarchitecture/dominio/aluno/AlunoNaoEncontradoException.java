package br.com.ffcs.javacleanarchitecture.dominio.aluno;

import br.com.ffcs.javacleanarchitecture.dominio.aluno.CPF;

public class AlunoNaoEncontradoException extends RuntimeException {

    public AlunoNaoEncontradoException(CPF cpf) {
        super("Aluno nao encontrado com CPF: " + cpf.getNumero());
    }
}
