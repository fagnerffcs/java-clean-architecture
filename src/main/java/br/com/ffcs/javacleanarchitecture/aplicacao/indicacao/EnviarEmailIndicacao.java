package br.com.ffcs.javacleanarchitecture.aplicacao.indicacao;

import br.com.ffcs.javacleanarchitecture.dominio.aluno.Aluno;

public interface EnviarEmailIndicacao {
    void enviarPara(Aluno indicado);
}
