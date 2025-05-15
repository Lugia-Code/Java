package com.lugiatracker.control;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.lugiatracker.dto.GerenteDTO;
import com.lugiatracker.model.Gerente;
import com.lugiatracker.model.Moto;
import com.lugiatracker.model.Patio;
import com.lugiatracker.repository.GerenteRepository;
import com.lugiatracker.repository.PatioRepository;
import com.lugiatracker.service.GerenteCashingService;
import com.lugiatracker.service.GerenteService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/gerentes")
public class GerenteController {

    @Autowired
    private GerenteRepository repG;

    @Autowired
    private GerenteCashingService cacheG;
    
    @Autowired
    private GerenteService servG;
    
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

    
    
    
    
    
    
    
    @Operation(description = "Esta operação possibilita na busca paginada dos gerentes",
    			summary = "busca paginada", tags="Busca paginada")
    @GetMapping(value="/paginada")
    public ResponseEntity<Page<GerenteDTO>> retornaGerentePaginados(
    		@RequestParam(value= "page", defaultValue = "0")Integer page,
    		@RequestParam(value= "size", defaultValue = "7")Integer size){
    	
    	PageRequest req = PageRequest.of(page,size);
    	Page<GerenteDTO> gerente_paginado = servG.paginar(req);
    
    	gerente_paginado.forEach(g ->{
    		
    	});

    	return ResponseEntity.ok(gerente_paginado);
    
    
    }
    
    
    
    @Operation(description = "Esta operação retorna todas as motos existentes "
      		+ "utilizando a estratégia de caching",
      		summary = "Retornar todas as motos utilizando caching", tags = "Retorno de Informação")
    	@GetMapping("/cacheable")
    	public List<Gerente>retornaTodasMotosCacheables(){
    	List<Gerente> todos_Gerentes =  cacheG.findAll();
    		
    		for(Gerente g : todos_Gerentes) {
    			g.add(linkTo(methodOn(GerenteController.class)
    			.retornaGerentePorID(g.getId_gerente()))
    			.withRel("Quer saber mais detalhes sobre ao gerente " + g.getId_gerente() + "?"));
    			
    			g.add(linkTo(methodOn(GerenteController.class)
    					.retornaGerentePaginados(null, null))
    					.withRel("Quer retornar Gerentes paginados?"));
    			
    			g.add(linkTo(methodOn(GerenteController.class)
    					.inserirGerente(null)).withRel("Quer inserir um novo gerente"));
    			
    			g.add(linkTo(methodOn(GerenteController.class)
    					.atualizarGerentePorId(g.getId_gerente(), null))
    					.withRel("Quer atualizar a moto " + g.getId_gerente() +"?"));
    		}
    		
    		return todos_Gerentes;
    		
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
