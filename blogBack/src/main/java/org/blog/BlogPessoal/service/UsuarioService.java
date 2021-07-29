package org.blog.BlogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.blog.BlogPessoal.model.UserLogin;
import org.blog.BlogPessoal.model.Usuario;
import org.blog.BlogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service // Anotação de serviço
public class UsuarioService {

	@Autowired
	private UsuarioRepository repositoryUsuario;

	/**
	 * Metodo que vai cadastrar o usuario e criptografar a senha do mesmo.
	 * 
	 * @param usuario
	 * @return repositoryUsuario.save(usuario)
	 * @author igor
	 * @since 1.0
	 */
	public Optional<Object> CadastrarUsuario(Usuario usuario) {

		return repositoryUsuario.findByEmail(usuario.getEmail()).map(usuarioExistente -> {
			return Optional.empty();
		}).orElseGet(() -> {

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			String senhaCriptografada = encoder.encode(usuario.getSenha());

			usuario.setSenha(senhaCriptografada);

			return Optional.ofNullable(repositoryUsuario.save(usuario));

		}); 

	};

	/**
	 * O metodo logar usuario vai verificar no sistema se a senha digitada bate com
	 * a senha cadastrada.
	 * 
	 * @param userLogin
	 * @return retorna um nulo ou um optional de userLogin
	 * @author igor
	 * @since 1.0
	 */
	public Optional<UserLogin> logarUsuario(Optional<UserLogin> userLogin) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		Optional<Usuario> usuario = repositoryUsuario.findByEmail(userLogin.get().getEmail());

		if (usuario.isPresent()) {
			if (encoder.matches(userLogin.get().getSenha(), usuario.get().getSenha())) {

				String auth = userLogin.get().getEmail() + ":" + userLogin.get().getSenha();

				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));

				String authHeader = "Basic " + new String(encodedAuth);

				userLogin.get().setToken(authHeader);

				userLogin.get().setNome(usuario.get().getNome());

				return userLogin;

			}
		}

		return null;

	};

}
