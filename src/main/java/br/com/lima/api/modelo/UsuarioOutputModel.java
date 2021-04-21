package br.com.lima.api.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsuarioOutputModel {

	@NotNull
	private Long id;

	@NotBlank
	private String nome;

	@NotBlank
	private Set<FilmeOutput> filmes = new HashSet<FilmeOutput>();

	public UsuarioOutputModel() {
	}

	public UsuarioOutputModel(@NotNull Long id, @NotBlank String nome, Set<FilmeOutput> filmes) {
		this.id = id;
		this.nome = nome;
		this.filmes = filmes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<FilmeOutput> getFilmes() {
		return filmes;
	}

	public void setFilmes(Set<FilmeOutput> filmes) {
		this.filmes = filmes;
	}

}
