package br.com.treinamento.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	@Bean
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return super.userDetailsServiceBean();
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception { // @formatter:off
        http.csrf().disable()
        .authorizeRequests()
        .anyRequest().permitAll();
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Poderia ser LDAP, Banco Relacional, mas aqui estamos utilizando em memoria
		// Dois usuarios: natanael e admin
		auth.inMemoryAuthentication().withUser("natanael.vaz@gmail.com")
		                             .password("{noop}javinha") //Esse noop indica que nao sera utilizado um passwordencoder
		                             .roles("USER")
		                             .and()
				                     .withUser("admin@gmail.com")
				                     .password("{noop}javinha")
				                     .roles("USER", "ADMIN");

	}

}
