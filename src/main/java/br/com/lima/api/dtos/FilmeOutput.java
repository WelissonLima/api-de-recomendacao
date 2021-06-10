package br.com.lima.api.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FilmeOutput {

	@NotBlank
	private String filme;

	@NotNull
	private Integer nota;

	public FilmeOutput(@NotBlank String filme, @NotNull Integer nota) {
		this.filme = filme;
		this.nota = nota;
	}

	public String getFilme() {
		return filme;
	}

	public void setFilme(String filme) {
		this.filme = filme;
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

}
