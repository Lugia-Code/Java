package com.lugiatracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.lugiatracker.model.Gerente;
import com.lugiatracker.repository.GerenteRepository;


@Service
public class GerenteCashingService {

	@Autowired
	private GerenteRepository repG;
	
	
	@Cacheable(value= "Gerentes")
	public List<Gerente> findAll(){
		return repG.findAll();
	}
	
	@Cacheable(value = "BuscaPorId", key= "#id_gerente")
	public Optional<Gerente> findById(Integer id_gerente){
		return repG.findById(id_gerente);
	}
	
	@Cacheable(value = "buscaPaginada", key = "#req")
	public Page<Gerente> findAll(PageRequest req) {
		return repG.findAll(req);
	}
	
	
}
