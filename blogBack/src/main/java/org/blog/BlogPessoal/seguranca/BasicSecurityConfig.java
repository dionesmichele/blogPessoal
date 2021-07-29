package org.blog.BlogPessoal.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override 
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("admin")).authorities("ROLE_ADMIN");
        
        auth.userDetailsService(userDetailsService);
    }
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	};
	
	/*
	 * Metodo que autoria ou bloqueia meus end point
	 */
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/usuarios/logar").permitAll()
		.antMatchers("/usuarios/cadastrar").permitAll() // permite tudo (userMaster)
		.anyRequest().authenticated() // tipo de autenticação
		.and().httpBasic() // Força de proteção
		.and().sessionManagement() // gerenciado de session
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // nossa api não vai guardar nenhuma session
		.and().cors() // abilitando o cors
		.and().csrf().disable() // desabilita o csrf, pois vamos usar uma configuração padrão personalizada
		;
	}
	
	
	
}
