package br.com.fiap.universidade_fiap.control;

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

import br.com.fiap.universidade_fiap.model.Funcao;
import br.com.fiap.universidade_fiap.model.Usuario;
import br.com.fiap.universidade_fiap.repository.FuncaoRepository;
import br.com.fiap.universidade_fiap.repository.UsuarioRepository;

@Controller
public class UsuarioController {

    @Autowired
    private FuncaoRepository repF;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UsuarioRepository repU;

    // Tela de login
    @GetMapping("/login")
    public ModelAndView logar() {
        return new ModelAndView("/login");
    }

    // Tela principal (index) -> evita o erro 404
    @GetMapping({"/", "/index"})
    public ModelAndView index() {
        return new ModelAndView("home/index");
    }

    // Tela de cadastro de usuário
    @GetMapping("/usuario/novo")
    public ModelAndView retornarCadUsuario() {
        ModelAndView mv = new ModelAndView("/usuario/novo");
        mv.addObject("usuario", new Usuario());
        mv.addObject("lista_funcoes", repF.findAll());
        return mv;
    }

    // Inserção de novo usuário
    @PostMapping("/insere_usuario")
    public ModelAndView inserirUsuario(Usuario usuario, @RequestParam(name = "id_funcao") Long id_funcao) {
        usuario.setSenha(encoder.encode(usuario.getSenha()));

        Set<Funcao> funcoes = new HashSet<>();

        if (id_funcao != null) {
            Optional<Funcao> funcao = repF.findById(id_funcao);
            funcao.ifPresent(funcoes::add);
        }

        usuario.setFuncoes(funcoes);
        repU.save(usuario);

        return new ModelAndView("redirect:/index");
    }

    // Página de acesso negado
    @GetMapping("/acesso_negado")
    public ModelAndView retornarPagAcessoNegado() {
        return new ModelAndView("/acesso_negado");
    }
}
