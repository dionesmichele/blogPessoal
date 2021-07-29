package org.blog.BlogPessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.blog.BlogPessoal.model.Postagem;
import org.blog.BlogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postagens") // EndPoint 
@CrossOrigin(origins = "*", allowedHeaders = "*") // Vai liberar todos os Headers // Assim ele irá aceitar qualquer API, seja angular ou outra
public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll(){
		
		return ResponseEntity.ok(repository.findAll());
		
	};
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable long id){
		
		/* 
		 * Passando o metodo map que irá me retornar um array
		*/
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
		
	};
	
	@GetMapping("/text/{text}")
	public ResponseEntity<List<Postagem>> GetByText(@PathVariable String text){
		
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(text));
		
	};
	
	// O metodo post sempre vai ser via body
	// você deverar passar o status de acordo a sua requi
	
	@PostMapping
	public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
		
	};
	
	// O metodo put é bem parecido com o post, porem tem as suas particuliaridades
	
	@PutMapping
	public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem){
		
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
		
	};
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id){
		
		repository.deleteById(id);
		
	};
}
