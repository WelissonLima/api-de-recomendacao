package br.com.lima.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SimilaridadeOutputModelo {

	@NotNull
	private Long usuario_id;

	@NotBlank
	private String usuario;

	@NotNull
	private double similaridade;

	public SimilaridadeOutputModelo(@NotNull Long usuario_id, @NotBlank String usuario, @NotNull double similaridade) {
		this.usuario_id = usuario_id;
		this.usuario = usuario;
		this.similaridade = similaridade;
	}

	public String getUsuario() {
		return usuario;
	}

	public Long getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Long usuario_id) {
		this.usuario_id = usuario_id;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public double getSimilaridade() {
		return similaridade;
	}

	public void setSimilaridade(double similaridade) {
		this.similaridade = similaridade;
	}

}
