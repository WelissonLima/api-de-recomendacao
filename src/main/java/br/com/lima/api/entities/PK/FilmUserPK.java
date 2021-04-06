package br.com.lima.api.entities.PK;


import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.lima.api.entities.Film;
import br.com.lima.api.entities.User;

//TERÁ UMA REFERÊNCIA PARA FILME E USUÁRIO
@Embeddable //POR SER UMA CLASSE AUXLILIAR DE CHAVE PRIMÁRIA COMPOSTA
public class FilmUserPK  implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "film_id")
	private Film film;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	//NÃO TERÁ OS CONSTRUTORES, somente os Getters e Setters
	
	public Film getFilm() {
		return film;
	}
	
	public void setFilm(Film film) {
		this.film = film;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((film == null) ? 0 : film.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		FilmUserPK other = (FilmUserPK) obj;
		if (film == null) {
			if (other.film != null)
				return false;
		} else if (!film.equals(other.film))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
		
}
