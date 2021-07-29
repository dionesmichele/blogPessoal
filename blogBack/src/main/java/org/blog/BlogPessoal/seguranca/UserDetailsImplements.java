package org.blog.BlogPessoal.seguranca;

import java.util.Collection;

import org.blog.BlogPessoal.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImplements implements UserDetails {

	/*
	 * Usado para controle interno.
	 */
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;

	/**
	 * Construtor que irá atribuir o nome e senha do meu usuario para os meus
	 * atributos da minha class de segurança UserDetailsImplements.
	 * 
	 * @param usuario
	 * @author igor
	 * @since 1.0
	 */
	public UserDetailsImplements(Usuario usuario) {

		this.userName = usuario.getNome();
		this.password = usuario.getSenha();
	};

	/**
	 * Contrutor vazio da minha class de segurança UserDetailsImplements.
	 * 
	 * @author igor
	 * @since 1.0
	 */
	public UserDetailsImplements() {
	};

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password; // foi passado o meu atributo de passaword.
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName; // foi passado o meu atributo de userName.
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true; // passando que a conta não vai ser expirada.
	}

	// Como boa prática vamos setar os outros métodos como true.

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
