package org.blog.BlogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.blog.BlogPessoal.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UsuarioRepositoryTest {
	
	public Usuario usuario = new Usuario("Zika", "jkdskhasdhskjdf", "zikaf24@gmail.com");
	
	public UsuarioRepository repositoryUsuario;

	@Autowired
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	
	@BeforeEach
	void start() {

		if(repositoryUsuario.findByEmail(usuario.getEmail()) != null) {
			repositoryUsuario.save(usuario);
		}

	};
	
//	@Test
//	void testValidaAtributos() {
//		Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuario);
//		System.out.println(violacao.toString());
//		assertTrue(violacao.isEmpty());
//	}


	@Test
	void findByEmail() throws Exception {

		Optional<Usuario> okUsuario = repositoryUsuario.findByEmail(usuario.getEmail());
		System.out.println(okUsuario.get().getEmail());
		Boolean ok = okUsuario.get().getEmail() == "zikaf24@gmail.com";
		Set<ConstraintViolation<Boolean>> violacao = validator.validate(ok);
		System.out.println(violacao.toString());
		assertTrue(violacao.isEmpty());
	};

}
