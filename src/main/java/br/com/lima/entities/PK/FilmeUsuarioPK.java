package br.com.lima.entities.PK;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.lima.entities.Filme;
import br.com.lima.entities.Usuario;

@Embeddable // Pois é uma classe auxiliar de chave primária composta
public class FilmeUsuarioPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "filme_id")
	private Filme filme;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usario;

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public Usuario getUsario() {
		return usario;
	}

	public void setUsario(Usuario usario) {
		this.usario = usario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filme == null) ? 0 : filme.hashCode());
		result = prime * result + ((usario == null) ? 0 : usario.hashCode());
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
		FilmeUsuarioPK other = (FilmeUsuarioPK) obj;
		if (filme == null) {
			if (other.filme != null)
				return false;
		} else if (!filme.equals(other.filme))
			return false;
		if (usario == null) {
			if (other.usario != null)
				return false;
		} else if (!usario.equals(other.usario))
			return false;
		return true;
	}

}
