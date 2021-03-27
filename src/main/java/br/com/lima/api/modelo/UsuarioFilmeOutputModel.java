package br.com.lima.api.modelo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsuarioFilmeOutputModel {

	@NotNull
	private Long id;

	@NotBlank
	private String usuario;

	@NotBlank
	private String filme;

	@NotNull
	private Integer nota;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}
}
