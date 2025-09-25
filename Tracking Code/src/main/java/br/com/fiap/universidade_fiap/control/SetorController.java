package br.com.fiap.universidade_fiap.control;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.universidade_fiap.model.EnumSetor;
import br.com.fiap.universidade_fiap.model.Setor;
import br.com.fiap.universidade_fiap.repository.SetorRepository;
import jakarta.validation.Valid;

@Controller
public class SetorController {

    @Autowired
    private SetorRepository repS;

    // Tela de menu de setores (com lista de setores)
    @GetMapping("/setor/menu")
    public ModelAndView menuSetor() {
        ModelAndView mv = new ModelAndView("/setor/menu");
        mv.addObject("setores", repS.findAll()); // adiciona a lista de setores
        return mv;
    }

    // Listar setores (outra tela de listagem, se ainda usar)
    @GetMapping("/setor")
    public ModelAndView listarSetores() {
        ModelAndView mv = new ModelAndView("/setor/setorIndex");
        mv.addObject("setores", repS.findAll()); // usar o mesmo nome para consistência
        return mv;
    }

    // Exibir formulário para novo setor
    @GetMapping("/setor/novo")
    public ModelAndView novoSetor() {
        ModelAndView mv = new ModelAndView("/setor/form");
        mv.addObject("setor", new Setor());
        mv.addObject("enumSetores", EnumSetor.values()); // necessário para popular o select
        return mv;
    }

    // Inserir setor
    @PostMapping("/setor/salvar")
    public ModelAndView salvarSetor(@Valid Setor setor, BindingResult bd) {
        if (bd.hasErrors()) {
            ModelAndView mv = new ModelAndView("/setor/form");
            mv.addObject("setor", setor);
            mv.addObject("enumSetores", EnumSetor.values()); // necessário para popular o select em caso de erro
            return mv;
        }
        repS.save(setor);
        return new ModelAndView("redirect:/setor/menu"); 
    }

    // Editar setor
    @GetMapping("/setor/editar/{id}")
    public ModelAndView editarSetor(@PathVariable Long id) {
        Optional<Setor> op = repS.findById(id);
        if (op.isPresent()) {
            ModelAndView mv = new ModelAndView("/setor/form");
            mv.addObject("setor", op.get());
            mv.addObject("enumSetores", EnumSetor.values()); // necessário para popular o select
            return mv;
        } else {
            return new ModelAndView("redirect:/setor/menu");
        }
    }

    // Remover setor
    @GetMapping("/setor/remover/{id}")
    public ModelAndView removerSetor(@PathVariable Long id) {
        Optional<Setor> op = repS.findById(id);
        if (op.isPresent()) {
            repS.deleteById(id);
        }
        return new ModelAndView("redirect:/setor/menu");
    }

}
