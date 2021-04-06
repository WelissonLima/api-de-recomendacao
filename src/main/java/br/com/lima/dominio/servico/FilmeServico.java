package br.com.lima.dominio.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lima.dominio.repositorio.FilmeRepositorio;

@Service
public class FilmeServico {
	
	@Autowired
	FilmeRepositorio filmeRepositorio;
}
