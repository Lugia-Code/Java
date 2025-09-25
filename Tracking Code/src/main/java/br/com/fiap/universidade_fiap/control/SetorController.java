package br.com.fiap.universidade_fiap.control;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.universidade_fiap.model.Setor;
import br.com.fiap.universidade_fiap.repository.SetorRepository;
import jakarta.validation.Valid;

@Controller
public class SetorController {

    @Autowired
    private SetorRepository repS;

    // Tela de menu de setores
    @GetMapping("/setor/menu")
    public ModelAndView menuSetor() {
        return new ModelAndView("/setor/menu");
    }

    // Listar setores
    @GetMapping("/setor")
    public ModelAndView listarSetores() {
        ModelAndView mv = new ModelAndView("/setor/setorIndex");
        mv.addObject("lista_setores", repS.findAll());
        return mv;
    }

    // Exibir formulário para novo setor
    @GetMapping("/setor/novo")
    public ModelAndView novoSetor() {
        ModelAndView mv = new ModelAndView("/setor/form");
        mv.addObject("setor", new Setor());
        return mv;
    }

    // Inserir setor
    @PostMapping("/setor/salvar")
    public ModelAndView salvarSetor(@Valid Setor setor, BindingResult bd) {
        if (bd.hasErrors()) {
            ModelAndView mv = new ModelAndView("/setor/form");
            mv.addObject("setor", setor);
            return mv;
        }
        repS.save(setor);
        return new ModelAndView("redirect:/setor");
    }

    // Editar setor
    @GetMapping("/setor/editar/{id}")
    public ModelAndView editarSetor(@PathVariable Long id) {
        Optional<Setor> op = repS.findById(id);
        if (op.isPresent()) {
            ModelAndView mv = new ModelAndView("/setor/form");
            mv.addObject("setor", op.get());
            return mv;
        } else {
            return new ModelAndView("redirect:/setor");
        }
    }

    // Remover setor
    @GetMapping("/setor/remover/{id}")
    public ModelAndView removerSetor(@PathVariable Long id) {
        Optional<Setor> op = repS.findById(id);
        if (op.isPresent()) {
            repS.deleteById(id);
        }
        return new ModelAndView("redirect:/setor");
    }

}
