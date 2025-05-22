package com.lugiatracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lugiatracker.model.Moto;

public interface MotoRepository  extends JpaRepository<Moto, Long>{

	 @Query("SELECT m FROM Moto m WHERE (:modelo = '' OR m.modelo = :modelo) AND (:placa = '' OR m.placa = :placa)")
	    List<Moto> buscarPorModeloEPlaca(@Param("modelo") String modelo, @Param("placa") String placa);

	Optional<Moto> findById(Long id);

	
	
}
