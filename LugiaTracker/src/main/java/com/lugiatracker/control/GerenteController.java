package com.lugiatracker.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.lugiatracker.model.Gerente;
import com.lugiatracker.model.Patio;
import com.lugiatracker.repository.GerenteRepository;
import com.lugiatracker.repository.PatioRepository;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/gerentes")
public class GerenteController {

    @Autowired
    private GerenteRepository repG;

    @Autowired
    private PatioRepository patioRepository; 

    
    
    @GetMapping
    @Operation(description = "Esta operação vai retornar todos os gerentes.")
    public List<Gerente> retornaTodosGerentes() {
        return repG.findAll();
    }

    
    
    @GetMapping(value = "/{id}")
    @Operation(description = "Esta operação vai retornar um gerente por seu ID.",
            summary = "Busca a informação do gerente", tags = "Localização de dados")
    public Gerente retornaGerentePorID(@PathVariable Integer id) {
        return repG.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gerente com ID " + id + " não encontrado."));
    }

    
    
    
    @Operation(description = "Esta operação possibilita a inserção de um novo gerente",
            summary = "Inserir um novo gerente", tags = "Inserção de Informação")
    @PostMapping(value = "/inserir")
    @ResponseStatus(HttpStatus.CREATED)
    public Gerente inserirGerente( @Valid @RequestBody Gerente gerente) {
        if (gerente.getPatio() != null && gerente.getPatio().getId_patio() != null) {
            Optional<Patio> patioOptional = patioRepository.findById(gerente.getPatio().getId_patio());
            if (patioOptional.isPresent()) {
                gerente.setPatio(patioOptional.get());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    "Pátio com ID " + gerente.getPatio().getId_patio() + " não encontrado.");
            }
        } 

        return repG.save(gerente);
    }

    
    
    
    @PutMapping(value = "/atualizar/{id}")
    @Operation(description = "Esta operação vai atualizar um gerente por seu ID.",
            summary = "Atualizar o gerente", tags = "Atualização de dados")
    public Gerente atualizarGerentePorId(@PathVariable Integer id, @Valid @RequestBody Gerente gerente) {
        Optional<Gerente> op = repG.findById(id);

        if (op.isPresent()) {
            Gerente conf_gerente = op.get();
            conf_gerente.setNome(gerente.getNome());
            conf_gerente.setLogin(gerente.getLogin());
            conf_gerente.setSenha(gerente.getSenha());

            
            Patio patioEnviado = gerente.getPatio();
            if (patioEnviado != null && patioEnviado.getId_patio() != null) {
                Optional<Patio> patioOptional = patioRepository.findById(patioEnviado.getId_patio());
                if (patioOptional.isPresent()) {
                    conf_gerente.setPatio(patioOptional.get());
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pátio com ID " + patioEnviado.getId_patio() + " não existe.");
                }
            } else {
                conf_gerente.setPatio(null); 
            }

            return repG.save(conf_gerente);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gerente com ID " + id + " não encontrado para atualização.");
        }
    }

    
    @DeleteMapping(value = "/excluir/{id}")
    @Operation(description = "Esta operação vai deletar o gerente por seu ID.",
            summary = "Remover um gerente", tags = "Remoção de dados")
    public Gerente deleteGerentePorId(@PathVariable Integer id) {
        Optional<Gerente> op = repG.findById(id);
        if (op.isPresent()) {
            Gerente ger_remover = op.get();
            repG.delete(ger_remover);
            return ger_remover;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gerente com ID " + id + " não encontrado para exclusão.");
        }
    }
    
    
    
}
