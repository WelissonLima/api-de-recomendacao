package br.com.lima.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsuarioFilmeOutputModel {

	@NotNull
	private Long id;

	@NotBlank
	private String user;

	@NotBlank
	private String film;

	@NotNull
	private Double note;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilm() {
		return film;
	}

	public void setFilme(String film) {
		this.film = film;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Double getNote() {
		return note;
	}

	public void setNote(Double note) {
		this.note = note;
	}

	
}
