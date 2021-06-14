package br.com.lima.domains;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.lima.domains.PK.UsuarioFilmePK;


@Entity
@Table(name = "tb_usuario_filme")
public class UsuarioFilme implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsuarioFilmePK id = new UsuarioFilmePK();
	
	private Double nota;

	

	public UsuarioFilme() {
		super();
	}

	public UsuarioFilme(Usuario usuario, Filme filme, Double nota) {
		super();
		id.setUsuario(usuario);
		id.setFilme(filme);
		this.nota = nota;
	}


	@JsonIgnore
	public Usuario getUsuario() {
		return id.getUsuario();
	}
	
	public void setUsuario(Usuario usuario) {
		id.setUsuario(usuario);
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
		UsuarioFilme other = (UsuarioFilme) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	
}
