package br.com.lima.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.lima.api.entities.Film;

public class FilmeResumoModelo {

	@NotNull
	private Double note;

	@NotBlank
	private Film film;

	public Double getNote() {
		return note;
	}

	public void setNote(Double note) {
		this.note = note;
	}

	public Film getFilme() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((film == null) ? 0 : film.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmeResumoModelo other = (FilmeResumoModelo) obj;
		if (film == null) {
			if (other.film != null)
				return false;
		} else if (!film.equals(other.film))
			return false;
		return true;
	}
	
	
}
