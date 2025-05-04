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
@Table(name="TBL_PATIO")
@Data
public class Patio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_patio;
	
	@NotEmpty(message= "Não é permitido a inserção de um patio sem nome.")
	@Column(name="nome")
	private String  nome;
	
	@NotEmpty(message= "Não é permitido a inserção de um patio sem a  localizacao.")
	@Column(name="localizacao")
	private String localização;
}
