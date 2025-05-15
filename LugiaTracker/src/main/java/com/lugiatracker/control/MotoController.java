package com.lugiatracker.control;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.lugiatracker.model.Moto;
import com.lugiatracker.repository.MotoRepository;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@RequestMapping(value= "/motos")
public class MotoController {
	
	
	@Autowired
	private MotoRepository repM;
	
	
	
	@Operation(description="essa operação busca e disponibiliza todas as motos",
			summary =  "Busca todas as motos", tags = "Localização de dados")
	@GetMapping
	public List<Moto>retornaTodasMotos() {
		return repM.findAll();
	}
	
	
	@Operation(description = "Esta operação busca a moto por seu chassi"
			,summary = "Busca moto por ID", tags = "Localização de dados")
	@GetMapping(value="/{chassi}")
	public Moto retornaMotoPorID(@PathVariable String chassi) {
		return repM.findById(chassi).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gerente com ID " + chassi + " não encontrado."));
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
	
	
}
