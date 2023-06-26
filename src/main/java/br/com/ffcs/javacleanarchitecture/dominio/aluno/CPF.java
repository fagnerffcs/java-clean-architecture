package br.com.ffcs.javacleanarchitecture.dominio.aluno;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CPF {

	private String numero;

	public CPF(String numero) {
		if (numero == null || 
				!numero.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")) {
			throw new IllegalArgumentException("CPF invalido!");
		}
		this.numero = numero;
	}

}
