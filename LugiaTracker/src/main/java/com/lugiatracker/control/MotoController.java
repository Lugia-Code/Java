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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.lugiatracker.dto.MotoDTO;
import com.lugiatracker.model.Moto;
import com.lugiatracker.repository.MotoRepository;
import com.lugiatracker.service.MotoCashingService;
import com.lugiatracker.service.MotoService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@RequestMapping(value= "/motos")
public class MotoController {
	
	
	@Autowired
	private MotoRepository repM;
	
	@Autowired
	private MotoCashingService cacheM;
	
	
	@Autowired
	private MotoService servM;
	
	
	
	
	@Hidden
	@Operation(description="essa operação busca e disponibiliza todas as motos",
			summary =  "Busca todas as motos", tags = "Localização de dados")
	@GetMapping
	public List<Moto>retornaTodasMotos() {
		return repM.findAll();
	}
	
	
  @Operation(description = "Esta operação possibilita na busca paginada dos gerentes",
  			summary = "busca paginada", tags="Busca paginada")
  @GetMapping(value="/paginada")
  public ResponseEntity<Page<MotoDTO>> retornaMotosPaginadas(
  		@RequestParam(value= "page", defaultValue = "0")Integer page,
  		@RequestParam(value= "size", defaultValue = "7")Integer size){
  	
  	PageRequest req = PageRequest.of(page,size);
  	Page<MotoDTO> moto_paginado = servM.paginar(req);
  
  	moto_paginado.forEach(g ->{
  		
  	});

  	return ResponseEntity.ok(moto_paginado);
  
  
  }
	
	
  @Operation(description = "Esta operação retorna todas as motos existentes "
  		+ "utilizando a estratégia de caching",
  		summary = "Retornar todas as motos utilizando caching", tags = "Retorno de Informação")
	@GetMapping("/cacheable")
	public List<Moto>retornaTodasMotosCacheables(){
	List<Moto> todas_motos =  cacheM.findAll();
		
		for(Moto m : todas_motos) {
			m.add(linkTo(methodOn(MotoController.class)
			.retornaMotoPorID(m.getChassi_moto()))
			.withRel("Quer saber mais detalhes sobre a moto " + m.getChassi_moto() + "?"));
			
			m.add(linkTo(methodOn(MotoController.class)
					.retornaMotosPaginadas(null, null))
					.withRel("Quer retornar músicas paginadas?"));
			
			m.add(linkTo(methodOn(MotoController.class)
					.adicionarMoto(null)).withRel("Quer inserir uma nova moto"));
			
			m.add(linkTo(methodOn(MotoController.class)
					.atualizaMotoPorId(m.getChassi_moto(), null))
					.withRel("Quer atualizar a moto " + m.getChassi_moto() +"?"));
		}
		
		return todas_motos;
		
	}
	
	
	
	
	@Operation(description = "Esta operação busca a moto por seu chassi"
			,summary = "Busca moto por ID", tags = "Localização de dados")
	@GetMapping(value="/{chassi}")
	public Moto retornaMotoPorID(@PathVariable String chassi) {
		return repM.findById(chassi).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gerente com ID " + chassi + " não encontrado."));
	}
	
	
	
	@Operation(description = "Busca motos filtrando por modelo, placa e setor (parâmetros opcionais)",
	           summary = "Busca filtrada de motos", tags = "Busca filtrada")
	@GetMapping("/buscar")
	public ResponseEntity<List<Moto>> buscarMotos(
	        @RequestParam(required = false, defaultValue = "") String modelo,
	        @RequestParam(required = false, defaultValue = "") String placa,
	        @RequestParam(required = false, defaultValue = "") String setor) {
	    
	    List<Moto> motosFiltradas = repM.buscarPorModeloEPlaca(
	        modelo, placa, setor);
	    
	    if(motosFiltradas.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    }
	    
	    return ResponseEntity.ok(motosFiltradas);
	}
	
	
	@PostMapping(value="/inserir")
	public Moto adicionarMoto(@RequestBody Moto moto) {
		repM.save(moto);
		moto.add(linkTo(methodOn(MotoController.class)
				.retornaMotoPorID(moto.getChassi_moto())).withRel("quer atualizar alguma moto?"));
		
		return moto;

	}
	
	@Operation(description = "Esta operação vai atualizar a moto por seu chassi")
	@PutMapping("/atualizar/{chassi}")
	public Moto atualizaMotoPorId(@PathVariable String chassi, @RequestBody Moto moto) {
		
		Optional<Moto> op = repM.findById(chassi);
		
		if (op.isPresent()) {
			Moto m_antiga = op.get();
			m_antiga.setModelo(moto.getModelo());
			m_antiga.setPlaca(moto.getPlaca());
			m_antiga.setSetor(moto.getSetor());
			m_antiga.setModelo(moto.getModelo());
			
			repM.save(m_antiga);
			return m_antiga;
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@Operation(description = "deletando as  motos por Id ",
	           summary = "exclusão filtrada de motos", tags = "Remoção de dados")
	@DeleteMapping(value="/excluir/{chassi}")
	public Moto DeletaMoto(@PathVariable String chassi) {
		
	Optional<Moto> op = repM.findById(chassi);
		
		if(op.isPresent()) {
			Moto m_remover = op.get();
			repM.delete(m_remover);
			return m_remover;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	
	   @Operation(
		        summary = "Buscar motos por modelo, placa e setor",
		        description = "Retorna uma lista de motos filtradas por modelo, placa e setor. " +
		                      "Se modelo ou placa forem vazios, não serão filtrados."
		    )
		    @GetMapping("/buscarPorModeloEPlaca")
		    public ResponseEntity<List<Moto>> buscarPorModeloEPlaca(
		        @Parameter(description = "Modelo da moto (opcional, vazio para ignorar)")
		        @RequestParam(defaultValue = "") String modelo,

		        @Parameter(description = "Placa da moto (opcional, vazio para ignorar)")
		        @RequestParam(defaultValue = "") String placa,

		        @Parameter(description = "Setor da moto (opcional)")
		        @RequestParam(required = false) String setor
		    ) {
		        List<Moto> motos = repM.buscarPorModeloEPlaca(modelo, placa, setor);
		        return ResponseEntity.ok(motos);
		    }
	
}
