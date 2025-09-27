package br.com.fiap.universidade_fiap.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.universidade_fiap.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByNome(String nome);

	Optional<Usuario> findById(Long id);
	  @Query("SELECT u FROM Usuario u " +
	           "LEFT JOIN FETCH u.setores " +
	           "LEFT JOIN FETCH u.funcoes " +
	           "WHERE u.id = :id")
	    Optional<Usuario> buscarUsuarioComSetoresEFuncoes(@Param("id") Long id);
}
