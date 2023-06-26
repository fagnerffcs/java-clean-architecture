package br.com.ffcs.javacleanarchitecture.dominio.indicacao;

import java.time.LocalDateTime;

import br.com.ffcs.javacleanarchitecture.dominio.aluno.Aluno;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Indicacao {

	private Aluno indicado;
	private Aluno indicante;
	private LocalDateTime dataIndicacao;

	public Indicacao(Aluno indicado, Aluno indicante) {
		this.indicado = indicado;
		this.indicante = indicante;
		this.dataIndicacao = LocalDateTime.now();
	}
}
