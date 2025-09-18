package com.lugiatracker.control;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lugiatracker.model.Funcao;
import com.lugiatracker.model.Usuario;
import com.lugiatracker.repository.FuncaoRepository;
import com.lugiatracker.repository.UsuarioRepository;


@Controller
public class UsuarioController {

	
	@Autowired
	private UsuarioRepository repU;
	
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private FuncaoRepository repF;
	
	
	@GetMapping("/login")
	public ModelAndView logar() {
		return new ModelAndView("/login");
	}

	@GetMapping("/usuario/novo")
	public ModelAndView retornarCadUsuario() {

		ModelAndView mv = new ModelAndView("/usuario/novo");

		mv.addObject("usuario", new Usuario());
		mv.addObject("lista_funcoes", repF.findAll());

		return mv;

	}

	@PostMapping("/insere_usuario")
	public ModelAndView inserirUsuario(Usuario usuario, @RequestParam(name = "id_funcao") Long id_funcao) {

		usuario.setSenha(encoder.encode(usuario.getSenha()));

		Set<Funcao> funcoes = new HashSet<Funcao>();

		if (id_funcao != null) {

			Optional<Funcao> funcao = repF.findById(id_funcao);

			if (funcao.isPresent()) {
				funcoes.add(funcao.get());
			}

		}

		usuario.setFuncoes(funcoes);

		repU.save(usuario);

		return new ModelAndView("redirect:/index");

	}
	
	
	@GetMapping("/acesso_negado")
	public ModelAndView retornarPagAcessoNegado() {
		return new ModelAndView("/acesso_negado");
	}
	
	
	
}
