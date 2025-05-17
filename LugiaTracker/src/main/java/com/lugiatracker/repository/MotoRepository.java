package com.lugiatracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lugiatracker.model.Moto;

public interface MotoRepository  extends JpaRepository<Moto, String>{

    List<Moto> findByModelo(
            String modelo, String placa, String setorNome);
	
	
}
