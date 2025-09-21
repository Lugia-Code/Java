package br.com.fiap.universidade_fiap.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcao")
public class Funcao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private EnumFuncao nome;

	public Funcao() {

	}

	public Funcao(Long id, EnumFuncao nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnumFuncao getNome() {
		return nome;
	}

	public void setNome(EnumFuncao nome) {
		this.nome = nome;
	}

}
