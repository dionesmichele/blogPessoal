package org.blog.BlogPessoal.repository;


import java.util.List;

import org.blog.BlogPessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// passando a anotação de repositório
@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>{ //Passando a Class e o tipy 
	
	public List<Postagem> findAllByTituloContainingIgnoreCase (String titulo);
	
	/* 
	 * find All By
	 * TituloContaining
	 * Ignore Case
	 *  
	 */
}
