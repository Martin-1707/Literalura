package com.aluraChallen.literalura;

import com.aluraChallen.literalura.Principal.Principal;
import com.aluraChallen.literalura.Repository.IAutorRepository;
import com.aluraChallen.literalura.Repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	@Autowired
	private ILibroRepository libroRepository;
	@Autowired
	private IAutorRepository autorRepository;


	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepository, autorRepository);
		principal.menu();
	}
}
