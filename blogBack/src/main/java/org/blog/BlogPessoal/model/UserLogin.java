package org.blog.BlogPessoal.model;

public class UserLogin {

	private long id;
	
	private String nome;

	private String email;

	private String senha;

	private String token;
	
	private String foto;
	
	private String tipo;

	public UserLogin() {
	}

	public UserLogin(long id,String nome, String email, String senha, String token) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.token = token;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
