package com.lugiatracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Entity
@Table(name="TBL_SETOR")
@Data
public class Setor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_setor;
	
	@NotEmpty(message= "Não é permitido a inserção de um setor sem nome.")
	@Column(name="nome")
	private String nome;
	
	@NotEmpty(message= "Não é permitido a inserção de uma descrição vazia.")
	@Column(name="descricao")
	private String descricao;
}
