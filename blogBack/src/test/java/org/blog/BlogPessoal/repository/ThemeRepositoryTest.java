package org.blog.BlogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.blog.BlogPessoal.model.Theme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ThemeRepositoryTest {

	@Autowired
	public ThemeRepository repositoryTestTheme;

	public Theme theme;

	@Autowired
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();

	@BeforeEach
	void start() {

		theme = new Theme("ZikaF24");
		if (repositoryTestTheme.findAllByDescricaoContainingIgnoreCase(theme.getDescricao()) != null) {
			repositoryTestTheme.save(theme);
		}

	}

	@Test
	void testValidaAtributos() {

		Boolean notOk = theme.getDescricao() == "oi";

		Set<ConstraintViolation<Boolean>> violacao = validator.validate(notOk);
		System.out.println(violacao.toString());
		assertFalse(notOk);
	}

	@Test
	void findAllByDescricaoContainingIgnoreCase() throws Exception {

		List<Theme> okTheme = repositoryTestTheme.findAllByDescricaoContainingIgnoreCase(theme.getDescricao());
		Boolean ok = okTheme.equals(theme);
		System.out.println(ok);
		assertFalse(ok);
	};

}
