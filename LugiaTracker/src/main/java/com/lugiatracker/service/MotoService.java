package com.lugiatracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lugiatracker.dto.MotoDTO;
import com.lugiatracker.model.Moto;
import com.lugiatracker.repository.MotoRepository;

@Service
public class MotoService {

	
	@Autowired
	private MotoRepository repM;
	
	
	@Autowired
	private MotoCashingService cacheM;
	
	
	@Transactional(readOnly = true)
	public Page<MotoDTO>paginar(PageRequest req){
		
		Page<Moto> motos = cacheM.findAll(req);
		
		return motos.map(i -> new MotoDTO(i));
	}
}
