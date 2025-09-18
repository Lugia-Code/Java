package com.lugiatracker.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lugiatracker.model.Usuario;
import com.lugiatracker.repository.UsuarioRepository;



@Service
public class FuncionarioService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repU;

	@Override
	public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
		
		Usuario usuario = repU.findByNomePerfil(nome).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!") );
		
		return new User(usuario.findByNomePerfil(nome),usuario.getSenha(),usuario.getFuncoes().stream()
				.map(funcao -> new SimpleGrantedAuthority(funcao.getNome().toString())).collect(Collectors.toList()));
		
	}
}