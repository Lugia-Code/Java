package com.lugiatracker.control;

import com.lugiatracker.model.Tag;
import com.lugiatracker.repository.TagRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tags")
public class TagController {

    private final TagRepository tagRepository;

    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    // LISTAR
    @GetMapping
    public String listarTags(Model model) {
        model.addAttribute("tags", tagRepository.findAll());
        return "tags/listar";
    }

    // FORM DE CRIAÇÃO
    @GetMapping("/novo")
    public String novaTagForm(Model model) {
        model.addAttribute("tag", new Tag());
        return "tags/form";
    }

    // SALVAR
    @PostMapping
    public String salvarTag(@ModelAttribute Tag tag) {
        tagRepository.save(tag);
        return "redirect:/tags";
    }

    // FORM DE EDIÇÃO
    @GetMapping("/editar/{id}")
    public String editarTag(@PathVariable("id") Long id, Model model) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Tag inválida:" + id));
        model.addAttribute("tag", tag);
        return "tags/form";
    }

    // DELETAR
    @GetMapping("/deletar/{id}")
    public String deletarTag(@PathVariable("id") Long id) {
        tagRepository.deleteById(id);
        return "redirect:/tags";
    }
}
