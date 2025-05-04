package com.lugiatracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name="TBL_VAGA")
@Data
public class Vaga {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer		id_vaga;
	
	@NotNull(message="Não é possível fazer a inserção de uma moto em uma vaga sem número.")
	@Column(name="numero")
	private Integer 	numero;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private StatusVaga status;
	

	@ManyToOne
	@JoinColumn(name= "id_patio")
	private Patio patio;
	
	public enum StatusVaga {
	    LIVRE,
	    OCUPADA
	}
	
}
