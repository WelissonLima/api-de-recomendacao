package br.com.lima.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RecomendacaoModelo {

	@NotBlank
	private String usuario;

	@NotBlank
	private String filme;

	@NotNull
	private Double nota;

	public RecomendacaoModelo(@NotBlank String usuario, @NotBlank String filme, @NotNull Double nota) {
		super();
		this.usuario = usuario;
		this.filme = filme;
		this.nota = nota;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getFilme() {
		return filme;
	}

	public void setFilme(String filme) {
		this.filme = filme;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

}
