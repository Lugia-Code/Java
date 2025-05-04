package com.lugiatracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Entity
@Table(name= "TBL_FUNCIONARIO")
@Data
public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_funcionario;
	
	
	@NotEmpty(message= "Não é permitido a inserção de um funcionario sem seu nome.")
	@Column(name="nome")
	private String  nome;
	
	@ManyToOne
	@JoinColumn(name="id_gerente")
	private Gerente gerente;
	
}
