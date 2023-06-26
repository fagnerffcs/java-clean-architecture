package br.com.ffcs.javacleanarchitecture.dominio.aluno;

public interface CifradorDeSenha {
    String cifrarSenha(String senha);
    boolean validarSenhaCifrada(String senhaCifrada, String senha);
}
