package br.com.lima.api.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.lima.api.entities.enums.FilmCategory;
import br.com.lima.model.UsuarioFilmeOutputModel;

@Entity
@Table(name = "tb_film")
public class Film implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Integer category;

	@JsonIgnore
	@OneToMany(mappedBy = "id.user")
	private Set<FilmUser> userFilms = new HashSet<>();

	public Film() {

	}

	public Film(Long id, String name, FilmCategory category) {
		super();
		this.id = id;
		this.name = name;
		setCategory(category);

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FilmCategory getCategory() {
		return FilmCategory.valueOf(category);
	}

	public void setCategory(FilmCategory category) {
		if (category != null) {
			this.category = category.getCode();
		}
	}

	public Set<FilmUser> getuserFilms() {
		return userFilms;
	}

	public void setFilmUser(Set<FilmUser> userFilms) {
		this.userFilms = userFilms;
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
		Film other = (Film) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public List<UsuarioFilmeOutputModel> getFilm() {
		// TODO Auto-generated method stub
		return null;
	}

}
