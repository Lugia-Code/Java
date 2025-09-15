package com.lugiatracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lugiatracker.model.Funcionarios;

public interface FuncionariosRepository extends JpaRepository<Funcionarios, Integer> {
	
	 @Query("SELECT f FROM usuario g ORDER BY f.nome")
	    List<Funcionarios> buscarTodosOrdenadosPorNome();

	    @Query("SELECT f FROM usuario f WHERE LOWER(f.login) LIKE LOWER(CONCAT('%', :login, '%'))")
	    List<Funcionarios> buscarPorLoginParcial(String login);
}
