package org.blog.BlogPessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_theme")
public class Theme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String descricao;

	/*
	 * One To Many vai me mapear de onde eu irei rirar os meus dados. no caso eu
	 * passo o atributo da minha class. O cascade informa que qualquer alteração que
	 * eu sofra em um tema, essa alteração será refletida para as demais. O Json
	 * Ignore Properties irá ignorar meu theme, quando chegar no meu atributo theme
	 * em Postagem.
	 */
	@OneToMany(mappedBy = "theme", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("theme")
	private List<Postagem> postagem;

	public Theme() {

	}

	public Theme(@NotNull String descricao) {

		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}

}
