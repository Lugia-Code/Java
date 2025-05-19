package com.lugiatracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lugiatracker.model.Gerente;

public interface GerenteRepository extends JpaRepository<Gerente, Integer> {
	
	 @Query("SELECT g FROM Gerente g ORDER BY g.nome")
	    List<Gerente> buscarTodosOrdenadosPorNome();

	    @Query("SELECT g FROM Gerente g WHERE LOWER(g.login) LIKE LOWER(CONCAT('%', :login, '%'))")
	    List<Gerente> buscarPorLoginParcial(String login);
}
