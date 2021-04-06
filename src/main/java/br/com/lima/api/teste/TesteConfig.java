package br.com.lima.api.teste;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.lima.api.entities.Film;
import br.com.lima.api.entities.FilmUser;
import br.com.lima.api.entities.User;
import br.com.lima.api.entities.enums.FilmCategory;
import br.com.lima.api.repositories.FilmRepository;
import br.com.lima.api.repositories.FilmUserRepository;
import br.com.lima.api.repositories.UserRepository;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner {

	@Autowired
	private UserRepository user;

	@Autowired
	private FilmRepository film;

	@Autowired
	private FilmUserRepository film_user;

	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Leticia Azevedo", "leticia@gmail.com", "61998622755");
		User u2 = new User(null, "Welisson Lima", "welisson@gmail.com", "61998562410");
		User u3 = new User(null, "Gabriel Renan", "gabriel@gmail.com", "61998622755");
		User u4 = new User(null, "Tercilio Aires", "tercilio@gmail.com", "61998562410");
		
		Film film1 = new Film(null, "Harry Potter", FilmCategory.SCIENCEFICTION);
		Film film2 = new Film(null, "Velozes e Furiosos", FilmCategory.ACTION);
		Film film3 = new Film(null, "Norbit", FilmCategory.COMEDY);
		Film film4 = new Film(null, "Kong", FilmCategory.SCIENCEFICTION);
		Film film5 = new Film(null, "GodZilla", FilmCategory.SCIENCEFICTION);
		Film film6 = new Film(null, "Anabelle", FilmCategory.COMEDY);
		Film film7 = new Film(null, "Aquaman", FilmCategory.ADVENTURE);
		Film film8 = new Film(null, "Invocação do Mal", FilmCategory.HORROR);
		
		FilmUser fu1 = new FilmUser(u1, film1, 4.5);
		FilmUser fu2 = new FilmUser(u2, film2, 3.2);
		FilmUser fu3 = new FilmUser(u3, film3, 4.3);
		FilmUser fu4 = new FilmUser(u4, film4, 5.0);
		FilmUser fu5 = new FilmUser(u1, film2, 4.5);
		FilmUser fu6 = new FilmUser(u2, film3, 3.2);
		FilmUser fu7 = new FilmUser(u3, film2, 4.3);
		FilmUser fu8 = new FilmUser(u4, film7, 5.0);
		
		user.saveAll(Arrays.asList(u1, u2, u3, u4));
		film.saveAll(Arrays.asList(film1, film2, film3, film4, film5, film6, film7, film8));
		film_user.saveAll(Arrays.asList(fu1, fu2, fu3, fu4, fu5, fu6, fu7, fu8));
		

	}
}