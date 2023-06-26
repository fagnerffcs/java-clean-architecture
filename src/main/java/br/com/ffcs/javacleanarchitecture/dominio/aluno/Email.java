package br.com.ffcs.javacleanarchitecture.dominio.aluno;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Email {
	
	private String endereco;

	public Email(String endereco) {
		if (endereco == null || 
				!endereco.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
			throw new IllegalArgumentException("E-mail invalido!");
		}

		this.endereco = endereco;
	}

}
