package org.blog.BlogPessoal.model;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ThemeTest {

	public Theme theme;
	
	@Autowired
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	
	@BeforeEach
	public void start() {
		
		theme = new Theme("Filme para maiores de 18 anos");
		
	};
	
	@Test
	void testValidaAtributos() {
		Set<ConstraintViolation<Theme>> violacao = validator.validate(theme);
		System.out.println(violacao.toString());
		assertTrue(violacao.isEmpty());
	}

}
