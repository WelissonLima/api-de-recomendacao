package br.com.lima.model;

import javax.validation.constraints.NotNull;

public class UserFilmInputModel {

	@NotNull
	private Double note;

	@NotNull
	private Long filme_id;

	@NotNull
	private Long usuario_id;

	public Double getNote() {
		return note;
	}

	public void setNote(Double note) {
		this.note = note;
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
