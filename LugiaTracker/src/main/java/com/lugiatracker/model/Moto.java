package com.lugiatracker.model;



import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Entity
@Table(name = "TBL_MOTO")
@Data
public class Moto extends RepresentationModel<Moto> {

	@Id
	@Pattern(regexp = "^[A-HJ-NPR-Z0-9]{17}$", message = "Formato de chassi inválido. O chassi deve ter 17 caracteres alfanuméricos.")
	private String chassi_moto;

	@NotEmpty(message= "Não é permitido a inserção de uma moto sem o modelo")
	@Column(name="modelo")
	private String modelo;
	
	@NotEmpty(message= "Não é permitido a inserção de uma moto sem a placa")
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{4}$", message = "Formato da placa inválido. Exemplo válido: ABC-1234")
	@Column(name="placa")
	private String placa;
	
	@ManyToOne
	@JoinColumn(name="id_setor")
	private Setor setor;
	

	
	
	
	
	
	
	
}
