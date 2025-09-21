package br.com.fiap.universidade_fiap.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fiap.universidade_fiap.model.Usuario;
import br.com.fiap.universidade_fiap.repository.UsuarioRepository;

@Service
public class UsuarioUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repU;

	@Override
	public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
		
		Usuario usuario = repU.findByNome(nome).orElseThrow(() -> new UsernameNotFoundException("Funcionario não encontrado!") );
		
		return new User(usuario.getNome(),usuario.getSenha(),usuario.getFuncoes().stream()
				.map(funcao -> new SimpleGrantedAuthority(funcao.getNome().toString())).collect(Collectors.toList()));
		
	}

}
