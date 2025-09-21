package br.com.fiap.universidade_fiap.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SegurancaConfig {
	
	@Bean
	public SecurityFilterChain filtrar(HttpSecurity http) throws Exception {
		
		http
			.authorizeHttpRequests(request -> request
				.requestMatchers(
					"/usuario/novo",
					"/pessoa/nova",
					"/discente/editar/{id}",
					"/discente/remover/{id}"
				).hasAuthority("GERENTE")
				.anyRequest().authenticated()
			)
			.formLogin(login -> login
				.loginPage("/login")
				.usernameParameter("nome")       
				.passwordParameter("password")   
				.defaultSuccessUrl("/index", true)
				.failureUrl("/login?falha=true")
				.permitAll()
			)
			.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout=true")
				.permitAll()
			)
			.exceptionHandling(exception -> 
				exception.accessDeniedHandler((request, response, ex) -> {
					response.sendRedirect("/acesso_negado");
				})
			);
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
