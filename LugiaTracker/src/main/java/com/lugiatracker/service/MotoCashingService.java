package com.lugiatracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import com.lugiatracker.model.Moto;
import com.lugiatracker.repository.MotoRepository;




@Service
public class MotoCashingService {

	
	@Autowired
	private MotoRepository repM;



	@Cacheable(value="motos")
	public List<Moto> findAll(){
		return repM.findAll();
	}
	

	@Cacheable(value = "BuscaPorIdMoto", key= "#chassi")
	public Optional<Moto> findById(Long id){
		return repM.findById(id);
	}
	
	@Cacheable(value = "buscaPaginada", key = "#req")
	public Page<Moto> findAll(PageRequest req) {
		return repM.findAll(req);
	}

}
