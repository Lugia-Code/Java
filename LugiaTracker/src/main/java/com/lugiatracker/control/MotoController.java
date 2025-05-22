package com.lugiatracker.control;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestBody;

import com.lugiatracker.dto.MotoDTO;
import com.lugiatracker.model.Moto;
import com.lugiatracker.repository.MotoRepository;
import com.lugiatracker.service.MotoCashingService;
import com.lugiatracker.service.MotoService;


import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value= "/motos")
public class MotoController {
	
	@Autowired
	private MotoRepository repM;
	
	@Autowired
	private MotoCashingService cacheM;
	
	@Autowired
	private MotoService servM;
	
	
	@Operation(description="Essa operação busca e disponibiliza todas as motos",
			summary =  "Busca todas as motos", tags = "Localização de dados")
	@GetMapping
	public List<Moto> retornaTodasMotos() {
		return repM.findAll();
	}
	
	@Operation(description = "Esta operação possibilita a busca paginada das motos",
  			summary = "Busca paginada", tags="Busca paginada")
	@GetMapping(value="/paginada")
	public ResponseEntity<Page<MotoDTO>> retornaMotosPaginadas(
  		@RequestParam(value= "page", defaultValue = "0") Integer page,
  		@RequestParam(value= "size", defaultValue = "7") Integer size){
  	
	  	PageRequest req = PageRequest.of(page, size);
	  	Page<MotoDTO> moto_paginado = servM.paginar(req);
	  	
	  	// Aqui pode adicionar links se quiser, por exemplo:
	  	// moto_paginado.forEach(motoDTO -> { ... });

	  	return ResponseEntity.ok(moto_paginado);
	}
	
	@Operation(description = "Esta operação retorna todas as motos existentes utilizando caching",
  		summary = "Retornar todas as motos utilizando caching", tags = "Retorno de Informação")
	@GetMapping("/cacheable")
	public List<Moto> retornaTodasMotosCacheables(){
		List<Moto> todas_motos = cacheM.findAll();
		
		for(Moto m : todas_motos) {
			m.add(linkTo(methodOn(MotoController.class)
					.retornaMotoPorID(m.getId()))
					.withRel("Quer saber mais detalhes sobre a moto " + m.getChassimoto() + "?"));
			
			m.add(linkTo(methodOn(MotoController.class)
					.retornaMotosPaginadas(null, null))
					.withRel("Quer retornar motos paginadas?"));
			
			m.add(linkTo(methodOn(MotoController.class)
					.adicionarMoto(null))
					.withRel("Quer inserir uma nova moto"));
			
			m.add(linkTo(methodOn(MotoController.class)
					.atualizaMotoPorId(m.getId(), null))
					.withRel("Quer atualizar a moto " + m.getChassimoto() +"?"));
		}
		
		return todas_motos;
	}
	
	@Operation(description = "Esta operação busca a moto por seu ID",
			summary = "Busca moto por ID", tags = "Localização de dados")
	@GetMapping(value="/{id}")
	public Moto retornaMotoPorID(@PathVariable Long id) {
		return repM.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto com ID " + id + " não encontrada."));
	}
	
	

	
	
	@PostMapping(value = "/inserir")
	public ResponseEntity<MotoDTO> adicionarMoto(@Valid @RequestBody MotoDTO motoDTO){
	    Moto moto = new Moto();
	    moto.setChassimoto(motoDTO.getChassimoto());
	    moto.setModelo(motoDTO.getModelo());
	    moto.setPlaca(motoDTO.getPlaca());
	    
	   
	    Moto motoSalva = repM.save(moto);

	    
	    MotoDTO motoSalvaDTO = new MotoDTO(motoSalva);
	  
	    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
	            .path("/{id}")
	            .buildAndExpand(motoSalva.getId())
	            .toUri();
	    return ResponseEntity.created(uri).body(motoSalvaDTO);
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<MotoDTO> atualizaMotoPorId(
	        @PathVariable Long id,
	        @Valid @RequestBody MotoDTO motoDTO) {

	    Optional<Moto> op = repM.findById(id);

	    if (op.isPresent()) {
	        Moto motoExistente = op.get();

	        
	        motoExistente.setModelo(motoDTO.getModelo());
	        motoExistente.setPlaca(motoDTO.getPlaca());
	        motoExistente.setChassimoto(motoDTO.getChassimoto());

	        Moto motoAtualizada = repM.save(motoExistente);

	        MotoDTO dtoAtualizada = new MotoDTO(motoAtualizada);

	      
	        dtoAtualizada.add(linkTo(methodOn(MotoController.class).retornaMotoPorID(id)).withSelfRel());
	        dtoAtualizada.add(linkTo(methodOn(MotoController.class).atualizaMotoPorId(id, motoDTO)).withRel("atualizar"));

	        return ResponseEntity.ok(dtoAtualizada);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	
	@Operation(description = "Deletando as motos por ID",
	           summary = "Exclusão filtrada de motos", tags = "Remoção de dados")
	@DeleteMapping(value="/excluir/{id}")
	public ResponseEntity<Void> deletaMoto(@PathVariable Long id) {
		
		Optional<Moto> op = repM.findById(id);
			
		if(op.isPresent()) {
			repM.delete(op.get());
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
