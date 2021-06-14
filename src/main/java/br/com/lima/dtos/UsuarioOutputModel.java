package br.com.lima.dtos;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import br.com.lima.domains.Usuario;

public class UsuarioOutputModel {

	@NotNull
	private Long id;

	@NotBlank
	private String nome;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String telefone;


	public UsuarioOutputModel() {
	}

	public UsuarioOutputModel(Usuario obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email= obj.getEmail();
		this.telefone = obj.getTelefone();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	

}
