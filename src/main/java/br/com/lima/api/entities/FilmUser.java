package br.com.lima.api.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.lima.api.entities.PK.FilmUserPK;

@Entity
@Table(name = "tb_UserFilm")
public class FilmUser implements Serializable {

	private static final long serialVersionUID = 1L;

	// Atributo identificador, correspondente a PK
	@EmbeddedId
	private FilmUserPK id = new FilmUserPK(); // SEMPRE QUE CRIAR UMA CLASSE AUXILIAR QUE E O ID COMPOSTO, TEM QUE
												// INSTANCIAR

	private Double note;

	public FilmUser() {
		super();
	}

	public FilmUser(User user, Film film, Double note) {
		super();
		id.setUser(user);
		id.setFilm(film);
		this.note = note;
	}

	@JsonIgnore
	public User getUser() {
		return id.getUser();
	}

	public void setUser(User user) {
		id.setUser(user);
	}

	public Film getFilm() {
		return id.getFilm();
	}

	public void setFilm(Film film) {
		id.setFilm(film);
	}

	public Double getNote() {
		return note;
	}

	public void setNote(Double note) {
		this.note = note;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		FilmUser other = (FilmUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
