package br.com.lima.teste;

import java.net.URL;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.lima.domains.Filme;
import br.com.lima.domains.Usuario;
import br.com.lima.domains.UsuarioFilme;
import br.com.lima.repositorios.FilmeRepositorio;
import br.com.lima.repositorios.UsuarioFilmeRepositorio;
import br.com.lima.repositorios.UsuarioRepositorio;

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

		Filme film1 = new Filme(null, "Harry Potter", (new URL("https://www.adorocinema.com/filmes/filme-29276/")));
		Filme film2 = new Filme(null, "Velozes e Furiosos", (new URL("https://www.adorocinema.com/filmes/filme-29276/")));
		Filme film3 = new Filme(null, "Norbit", (new URL("https://www.adorocinema.com/filmes/filme-29276/")));
		Filme film4 = new Filme(null, "Kong", (new URL("https://www.adorocinema.com/filmes/filme-29276/")));
		Filme film5 = new Filme(null, "GodZilla", (new URL("https://www.adorocinema.com/filmes/filme-29276/")));
		Filme film6 = new Filme(null, "Anabelle", (new URL("https://www.adorocinema.com/filmes/filme-29276/")));
		Filme film7 = new Filme(null, "Aquaman", (new URL("https://www.adorocinema.com/filmes/filme-29276/")));
		Filme film8 = new Filme(null, "Invocação do Mal", (new URL("https://www.adorocinema.com/filmes/filme-29276/")));
		Filme film9 = new Filme(null, "Sr & Sra Smith", (new URL("https://www.adorocinema.com/filmes/filme-29276/")));
		Filme film10 = new Filme(null, "Shrek", (new URL("https://www.adorocinema.com/filmes/filme-29276/")));
		Filme film11 = new Filme(null, "Meu Malvado Favorito", (new URL("https://www.adorocinema.com/filmes/filme-29276/")));

		UsuarioFilme fu1 = new UsuarioFilme( u1, film1, 4.0);
		UsuarioFilme fu2 = new UsuarioFilme( u1, film3, 4.0);
		UsuarioFilme fu3 = new UsuarioFilme( u1, film9, 3.0);

		UsuarioFilme fu4 = new UsuarioFilme(u2, film11, 2.0);
		UsuarioFilme fu5 = new UsuarioFilme(u2, film2, 5.0);
		UsuarioFilme fu6 = new UsuarioFilme( u2, film1, 4.0);

		UsuarioFilme fu7 = new UsuarioFilme(u3, film3, 2.0);
		UsuarioFilme fu8 = new UsuarioFilme(u3, film10, 4.0);
		UsuarioFilme fu9 = new UsuarioFilme(u3, film8, 1.0);
		UsuarioFilme fu10 = new UsuarioFilme(u3, film2, 3.0);

		UsuarioFilme fu11 = new UsuarioFilme(u4, film4, 2.0);
		UsuarioFilme fu12 = new UsuarioFilme(u4, film8, 4.0);
		UsuarioFilme fu13 = new UsuarioFilme(u4, film1, 3.0);		
		UsuarioFilme fu14 = new UsuarioFilme(u4, film5, 1.0);

		UsuarioFilme fu15 = new UsuarioFilme(u5, film10, 5.0);
		UsuarioFilme fu16 = new UsuarioFilme(u5, film11, 2.0);
		UsuarioFilme fu17 = new UsuarioFilme(u5, film9, 4.0);
		
		UsuarioFilme fu18 = new UsuarioFilme(u6, film3, 2.0);
		UsuarioFilme fu19 = new UsuarioFilme(u6, film11, 3.0);
		UsuarioFilme fu20 = new UsuarioFilme(u6, film7, 5.0);

		UsuarioFilme fu21 = new UsuarioFilme(u7, film8, 1.0);
		UsuarioFilme fu22 = new UsuarioFilme(u7, film2, 3.0);
		UsuarioFilme fu23 = new UsuarioFilme(u7, film4, 2.0);
		UsuarioFilme fu24 = new UsuarioFilme(u7, film3, 3.0);
		
		user.saveAll(Arrays.asList(u1, u2, u3, u4, u5, u6, u7));
		film.saveAll(Arrays.asList(film1, film2, film3, film4, film5, film6, film7, film8, film9, film10, film11));
		user_film.saveAll(Arrays.asList(fu1, fu2, fu3, fu4, fu5, fu6, fu7, fu8, fu9, fu10, fu11, fu12, fu13, fu14, fu15,
				fu16, fu17, fu18, fu19, fu20, fu21, fu22, fu23, fu24));
	}
}
