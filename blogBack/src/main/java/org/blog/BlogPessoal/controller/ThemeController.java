package org.blog.BlogPessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.blog.BlogPessoal.model.Theme;
import org.blog.BlogPessoal.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*") // Vai liberar todos os Headers
@RequestMapping("/theme")
public class ThemeController {

	@Autowired
	private ThemeRepository repository;

	@GetMapping
	public ResponseEntity<List<Theme>> getAll() {

		return ResponseEntity.ok(repository.findAll());

	};

	@GetMapping("/{id}")
	public ResponseEntity<Theme> getById(@PathVariable Long id) {

		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());

	};
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Theme>> getByName(@PathVariable String descricao) {

		return ResponseEntity
				.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));

	};
	
	@PostMapping
	public ResponseEntity<Theme> post(@Valid @RequestBody Theme theme){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(theme));
	};
	
	@PutMapping
	public ResponseEntity<Theme> put(@Valid @RequestBody Theme theme){
		
		return ResponseEntity.ok(repository.save(theme));
		
	};
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		
		repository.deleteById(id);
		
	};

}
