package org.blog.BlogPessoal.repository;

import java.util.List;

import org.blog.BlogPessoal.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//passando a anotação de repositório
@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long>{
	public List<Theme> findAllByDescricaoContainingIgnoreCase(String descricao);
}
