package org.blog.BlogPessoal.controller;


import java.util.Optional;

import javax.validation.Valid;

import org.blog.BlogPessoal.model.UserLogin;
import org.blog.BlogPessoal.model.Usuario;
import org.blog.BlogPessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*") // Vai liberar todos os Headers
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService serviceUsuario;
	
	/*
	 * por padrão e segurança os metodos usuarios e loginUsuario
	 * devem ser feitos pelo body
	 * 
	 */
	
	@PostMapping("/logar")
	public ResponseEntity<UserLogin> Autentication(@Valid @RequestBody Optional<UserLogin> userLogin){
		
		return serviceUsuario.logarUsuario(userLogin)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	};
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Object> Autentication(@Valid @RequestBody Usuario usuario){
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(serviceUsuario.CadastrarUsuario(usuario));
	};
	
}
