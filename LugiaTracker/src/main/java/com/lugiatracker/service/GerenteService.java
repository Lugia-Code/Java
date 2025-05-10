package com.lugiatracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lugiatracker.dto.GerenteDTO;
import com.lugiatracker.model.Gerente;
import com.lugiatracker.repository.GerenteRepository;



@Service
public class GerenteService {
	
	@Autowired
	private GerenteRepository repG;
	
	@Autowired
	private GerenteCashingService cacheG;
	
	
	@Transactional(readOnly = true)
	public Page<GerenteDTO>paginar(PageRequest req){
		
		Page<Gerente> gerentes = cacheG.findAll(req);
		
		return gerentes.map(i -> new GerenteDTO(i));
	}
}
