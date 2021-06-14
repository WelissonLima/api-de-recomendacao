package br.com.lima.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsuarioFilmeOutputModel {

	

	@NotBlank
	private String usuario;

	@NotBlank
	private String filme;

	@NotNull
	private Double nota;


	public String getFilme() {
		return filme;
	}

	public void setFilme(String filme) {
		this.filme = filme;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	
}
