package br.com.lima.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.validation.constraints.NotNull;

import br.com.lima.entities.PK.FilmeUsuarioPK;

public class UsuarioFilmepk implements Serializable {

	private static final long serialVersionUID = 1L;

	
	//Atributo identificador, correspondente a PK
	@EmbeddedId
	private FilmeUsuarioPK id = new FilmeUsuarioPK();
	
	@NotNull
	private Double nota;
	
	public UsuarioFilmepk() {
		super();
	}

	public UsuarioFilmepk(Usuario usuario, Filme filme, @NotNull Double nota) {
		super();
		id.setUsario(usuario);
		id.setFilme(filme);
		this.nota = nota;
	}

	
	public Usuario getUsuario() {
		return id.getUsario();
	}
	
	public void setUsuario(Usuario usuario) {
	    id.setUsario(usuario);
	}
	
	public Filme getFilme() {
		return id.getFilme();
	}
	
	public void setFilme(Filme filme) {
		id.setFilme(filme);
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
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
		UsuarioFilmepk other = (UsuarioFilmepk) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
