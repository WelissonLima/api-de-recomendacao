package br.com.lima.api.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.lima.api.domains.Filme;

public class FilmeResumoModelo {

	@NotNull
	private Double nota;

	@NotBlank
	private Filme filme;

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filme == null) ? 0 : filme.hashCode());
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
		if (filme == null) {
			if (other.filme != null)
				return false;
		} else if (!filme.equals(other.filme))
			return false;
		return true;
	}



}
