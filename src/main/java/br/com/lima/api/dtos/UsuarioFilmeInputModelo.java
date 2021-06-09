package br.com.lima.api.dtos;

import javax.validation.constraints.NotNull;

public class UsuarioFilmeInputModelo {

	@NotNull
	private Integer nota;

	@NotNull
	private Long filme_id;

	@NotNull
	private Long usuario_id;

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	public Long getFilme_id() {
		return filme_id;
	}

	public void setFilme_id(Long filme_id) {
		this.filme_id = filme_id;
	}

	public Long getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Long usuario_id) {
		this.usuario_id = usuario_id;
	}
}
