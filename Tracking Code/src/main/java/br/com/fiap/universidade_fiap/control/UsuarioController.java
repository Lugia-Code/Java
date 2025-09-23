package br.com.fiap.universidade_fiap.control;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.universidade_fiap.model.Funcao;
import br.com.fiap.universidade_fiap.model.Usuario;
import br.com.fiap.universidade_fiap.repository.FuncaoRepository;
import br.com.fiap.universidade_fiap.repository.UsuarioRepository;
import jakarta.validation.Valid;

@Controller
public class UsuarioController {

    @Autowired
    private FuncaoRepository repF;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UsuarioRepository repU;

    
    
    @GetMapping("/login")
    public ModelAndView login(@RequestParam(value="falha", required=false) String falha) {
        ModelAndView mv = new ModelAndView("login"); 
        if(falha != null) mv.addObject("erro", true);
        return mv;
    }
    
    
    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/home/index");
        List<Usuario> users = repU.findAll();
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Usuario> op = repU.findByNome(auth.getName());
        
        if (op.isPresent()) {
            mv.addObject("usuario", op.get());
        }
        mv.addObject("usuarios", users);
        return mv;
    }

    @GetMapping("/usuario/novo")
    public ModelAndView retornarCadUsuario() {
        ModelAndView mv = new ModelAndView("usuario/form_cad");
        mv.addObject("usuario", new Usuario());
        mv.addObject("lista_funcoes", repF.findAll());
        return mv;
    }

    @PostMapping("/insere_usuario")
    public ModelAndView inserirUsuario(@Valid Usuario usuario, BindingResult bd, @RequestParam(name = "id_funcao") Long id_funcao) {
        if (bd.hasErrors()) {
            ModelAndView mv = new ModelAndView("usuario/form_cad");
            mv.addObject("usuario", usuario);
            mv.addObject("lista_funcoes", repF.findAll());
            return mv;
        } else {
            usuario.setSenha(encoder.encode(usuario.getSenha()));
            Set<Funcao> funcoes = new HashSet<>();
            Optional<Funcao> funcao = repF.findById(id_funcao);
            funcao.ifPresent(funcoes::add);

            usuario.setFuncoes(funcoes);
            repU.save(usuario);
            return new ModelAndView("redirect:/index");
        }
    }

    @GetMapping("/usuario/editar/{id}")
    public ModelAndView exibirPaginaEdicao(@PathVariable Long id) {
        Optional<Usuario> op = repU.findById(id);
        if (op.isPresent()) {
            ModelAndView mv = new ModelAndView("usuario/edicao");
            mv.addObject("usuario", op.get());
            mv.addObject("lista_funcoes", repF.findAll());
            return mv;
        } else {
            return new ModelAndView("redirect:/index");
        }
    }

    @PostMapping("/usuario/atualizar/{id}")
    public ModelAndView atualizarUsuario(@PathVariable Long id, @Valid Usuario usuarioAtualizado, BindingResult bd, @RequestParam(name = "id_funcao") Long id_funcao) {
        if (bd.hasErrors()) {
            ModelAndView mv = new ModelAndView("usuario/edicao");
            mv.addObject("usuario", usuarioAtualizado);
            mv.addObject("listaFuncoes", repF.findAll());
            return mv;
        } else {
            Optional<Usuario> op = repU.findById(id);
            if (op.isPresent()) {
                Usuario usuario = op.get();
                usuario.setNome(usuarioAtualizado.getNome());
                usuario.setSenha(encoder.encode(usuarioAtualizado.getSenha()));
                
                Set<Funcao> funcoes = new HashSet<>();
                Optional<Funcao> funcao = repF.findById(id_funcao);
                funcao.ifPresent(funcoes::add);
                
                usuario.setFuncoes(funcoes);
                repU.save(usuario);
                return new ModelAndView("redirect:/index");
            } else {
                return new ModelAndView("redirect:/index");
            }
        }
    }

    @GetMapping("/usuario/remover/{id}")
    public ModelAndView removerUsuario(@PathVariable Long id) {
        Optional<Usuario> op = repU.findById(id);
        if (op.isPresent()) {
            repU.deleteById(id);
            return new ModelAndView("redirect:/index");
        } else {
            return new ModelAndView("redirect:/index");
        }
    }
}
