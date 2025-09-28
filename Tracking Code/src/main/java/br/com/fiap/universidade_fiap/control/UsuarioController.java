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

import br.com.fiap.universidade_fiap.model.Endereco;
import br.com.fiap.universidade_fiap.model.Funcao;
import br.com.fiap.universidade_fiap.model.Setor;
import br.com.fiap.universidade_fiap.model.Usuario;
import br.com.fiap.universidade_fiap.repository.EnderecoRepository;
import br.com.fiap.universidade_fiap.repository.FuncaoRepository;
import br.com.fiap.universidade_fiap.repository.SetorRepository;
import br.com.fiap.universidade_fiap.repository.UsuarioRepository;
import br.com.fiap.universidade_fiap.service.ViaCep;
import jakarta.validation.Valid;

@Controller
public class UsuarioController {

    @Autowired
    private FuncaoRepository repF;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UsuarioRepository repU;
    @Autowired
    private SetorRepository repS;
    @Autowired
    private EnderecoRepository repE;
    
    @Autowired
    private ViaCep viaCep;
	
    @GetMapping("/login")
    public ModelAndView login(@RequestParam(value="falha", required=false) String falha) {
        ModelAndView mv = new ModelAndView("login"); 
        if(falha != null) mv.addObject("erro", true);
        return mv;
    }
    
    @GetMapping("/acesso-negado")
    public String acessoNegado() {
        return "usuario/acesso_negado"; 
    }
    
    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/home/index");
        List<Usuario> users = repU.findAll();
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Usuario> op = repU.findByNome(auth.getName());
        
        op.ifPresent(usuario -> mv.addObject("usuario", usuario));
        mv.addObject("usuarios", users);
        return mv;
    }

    @GetMapping("/usuario/novo")
    public ModelAndView retornarCadUsuario() {
        ModelAndView mv = new ModelAndView("usuario/form_cad");
        mv.addObject("usuario", new Usuario());
        mv.addObject("lista_funcoes", repF.findAll());
        mv.addObject("lista_setores", repS.findAll());
        return mv;
    }
    
    @PostMapping("/insere_usuario")
    public ModelAndView inserirUsuario(
            @Valid Usuario usuario,
            BindingResult bd,
            @RequestParam(name = "id_funcao") Long id_funcao,
            @RequestParam(name = "id_setor") Long id_setor,
            @RequestParam(name = "cep") String cep) { 

        if (bd.hasErrors()) {
            ModelAndView mv = new ModelAndView("usuario/form_cad");
            mv.addObject("usuario", usuario);
            mv.addObject("lista_funcoes", repF.findAll());
            mv.addObject("lista_setores", repS.findAll());
            return mv;
        } else {
            usuario.setSenha(encoder.encode(usuario.getSenha()));

            usuario.setId_setor(id_setor);
            Set<Setor> setores = new HashSet<>();
            repS.findById(id_setor).ifPresent(setores::add);
            usuario.setSetores(setores);

            Set<Funcao> funcoes = new HashSet<>();
            repF.findById(id_funcao).ifPresent(funcoes::add);
            usuario.setFuncoes(funcoes);

            Endereco endereco = viaCep.buscarEnderecoPorCep(cep);
            if (endereco != null) {
                repE.save(endereco);
                usuario.getEnderecos().add(endereco);
            }

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
            mv.addObject("lista_setores", repS.findAll());
            return mv;
        } else {
            return new ModelAndView("redirect:/index");
        }
    }
    
    @PostMapping("/usuario/atualizar/{id}")
    public ModelAndView atualizarUsuario(
            @PathVariable Long id,
            @Valid Usuario usuarioAtualizado,
            BindingResult bd,
            @RequestParam(name = "id_setor", required = false) Long id_setor,
            @RequestParam(name = "cep", required = false) String cep) {

        if (bd.hasErrors()) {
            ModelAndView mv = new ModelAndView("usuario/edicao");
            mv.addObject("usuario", usuarioAtualizado);
            mv.addObject("lista_funcoes", repF.findAll());
            mv.addObject("lista_setores", repS.findAll());
            return mv;
        }

        Optional<Usuario> op = repU.findById(id);
        if (op.isPresent()) {
            Usuario usuario = op.get();
            usuario.setNome(usuarioAtualizado.getNome());

            if (usuarioAtualizado.getSenha() != null && !usuarioAtualizado.getSenha().isEmpty()) {
                usuario.setSenha(encoder.encode(usuarioAtualizado.getSenha()));
            }

            Set<Funcao> funcoes = new HashSet<>();
            if (usuarioAtualizado.getFuncoes() != null) {
                funcoes.addAll(usuarioAtualizado.getFuncoes());
            }
            usuario.setFuncoes(funcoes);

            if (id_setor != null) {
                Set<Setor> setores = new HashSet<>();
                repS.findById(id_setor).ifPresent(setores::add);
                usuario.setSetores(setores);
            }

            if (cep != null && !cep.isEmpty()) {
                Endereco enderecoAtualizado = viaCep.buscarEnderecoPorCep(cep);
                if (enderecoAtualizado != null) {
                    usuario.getEnderecos().clear(); 
                    repE.save(enderecoAtualizado);  
                    usuario.getEnderecos().add(enderecoAtualizado); 
                }
            }

            repU.save(usuario);
        }

        return new ModelAndView("redirect:/index");
    }

    @GetMapping("/usuario/remover/{id}")
    public ModelAndView removerUsuario(@PathVariable Long id) {
        Optional<Usuario> op = repU.findById(id);
        op.ifPresent(usuario -> repU.deleteById(id));
        return new ModelAndView("redirect:/index");
    }
    
    @GetMapping("/usuario/{id}")
    public ModelAndView detalhesUsuario(@PathVariable Long id) {
        Optional<Usuario> op = repU.buscarUsuarioComSetoresEFuncoes(id);
        if (op.isPresent()) {
            Usuario usuario = op.get();
            ModelAndView mv = new ModelAndView("usuario/detalhes");
            mv.addObject("usuario", usuario);
            return mv;
        } else {
            return new ModelAndView("redirect:/index");
        }
    }
}
