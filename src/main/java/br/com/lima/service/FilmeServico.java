package br.com.lima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lima.repository.FilmeRepositorio;

@Service
public class FilmeServico {
	
	@Autowired
	FilmeRepositorio filmeRepositorio;
}
