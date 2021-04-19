package br.com.lima.teste;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.lima.dominio.modelo.Filme;
import br.com.lima.dominio.modelo.Usuario;
import br.com.lima.dominio.modelo.UsuarioFilme;
import br.com.lima.dominio.repositorio.FilmeRepositorio;
import br.com.lima.dominio.repositorio.UsuarioFilmeRepositorio;
import br.com.lima.dominio.repositorio.UsuarioRepositorio;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner {

	@Autowired
	private UsuarioRepositorio user;

	@Autowired
	private FilmeRepositorio film;

	@Autowired
	private UsuarioFilmeRepositorio user_film;

	@Override
	public void run(String... args) throws Exception {

		Usuario u1 = new Usuario(null, "Leticia Azevedo", "leticia@gmail.com", "61998622755");
		Usuario u2 = new Usuario(null, "Welisson Lima", "welisson@gmail.com", "61998562410");
		Usuario u3 = new Usuario(null, "Gabriel Renan", "gabriel@gmail.com", "61998622755");
		Usuario u4 = new Usuario(null, "Tercilio Aires", "tercilio@gmail.com", "61998562410");
		Usuario u5 = new Usuario(null, "Ana Gabriela", "anaGabriela@gmail.com", "61997365423");
		Usuario u6 = new Usuario(null, "Fernanda Souza", "fernandaSouza@gmail.com", "61984562411");
		Usuario u7 = new Usuario(null, "João Gabriel", "joaoGabriel@gmail.com", "61999078634");

		Filme film1 = new Filme(null, "Harry Potter");
		Filme film2 = new Filme(null, "Velozes e Furiosos");
		Filme film3 = new Filme(null, "Norbit");
		Filme film4 = new Filme(null, "Kong");
		Filme film5 = new Filme(null, "GodZilla");
		Filme film6 = new Filme(null, "Anabelle");
		Filme film7 = new Filme(null, "Aquaman");
		Filme film8 = new Filme(null, "Invocação do Mal");
		Filme film9 = new Filme(null, "Sr & Sra Smith");
		Filme film10 = new Filme(null, "Shrek");
		Filme film11 = new Filme(null, "Meu Malvado Favorito");

		UsuarioFilme fu1 = new UsuarioFilme(null, 5, film1, u1);
		UsuarioFilme fu5 = new UsuarioFilme(null, 4, film3, u1);
		UsuarioFilme fu12 = new UsuarioFilme(null, 3, film9, u1);
		
		UsuarioFilme fu6 = new UsuarioFilme(null, 2, film11, u2);
		UsuarioFilme fu2 = new UsuarioFilme(null, 5, film2, u2);
		UsuarioFilme fu9 = new UsuarioFilme(null, 5, film1, u2);

		UsuarioFilme fu3 = new UsuarioFilme(null, 2, film3, u3);
		UsuarioFilme fu7 = new UsuarioFilme(null, 4, film10, u3);
		UsuarioFilme fu10 = new UsuarioFilme(null, 5, film2, u3);
		
		UsuarioFilme fu4 = new UsuarioFilme(null, 4, film4, u4);
		UsuarioFilme fu8 = new UsuarioFilme(null, 3, film8, u4);
		UsuarioFilme fu11 = new UsuarioFilme(null, 5, film5, u4);
		
		UsuarioFilme fu13 = new UsuarioFilme(null, 5, film10, u5);
		UsuarioFilme fu14 = new UsuarioFilme(null, 2, film11, u5);
		UsuarioFilme fu15 = new UsuarioFilme(null, 4, film9, u5);
		
		UsuarioFilme fu16 = new UsuarioFilme(null, 2, film3, u6);
		UsuarioFilme fu17 = new UsuarioFilme(null, 3, film11, u6);
		UsuarioFilme fu18 = new UsuarioFilme(null, 5, film7, u6);
		
		user.saveAll(Arrays.asList(u1, u2, u3, u4, u5, u6, u7));
		film.saveAll(Arrays.asList(film1, film2, film3, film4, film5, film6, film7, film8,film9, film10, film11));
		user_film.saveAll(Arrays.asList(fu1, fu2, fu3, fu4, fu5, fu6, fu7, fu8, fu9, fu10, fu11, fu12, fu13, fu14, fu15, fu16,fu17,fu18));

	}

}
