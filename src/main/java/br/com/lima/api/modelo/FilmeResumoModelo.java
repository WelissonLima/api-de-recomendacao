package br.com.lima.api.modelo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.lima.dominio.modelo.Filme;

public class FilmeResumoModelo {

	@NotNull
	private int nota;

	@NotBlank
	private Filme filme;

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}
}
