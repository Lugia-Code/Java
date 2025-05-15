package com.lugiatracker.dto;

import org.springframework.hateoas.RepresentationModel;

import com.lugiatracker.model.Moto;
import com.lugiatracker.model.Setor;

import lombok.Data;



@Data
public class MotoDTO extends RepresentationModel<Moto> {

	
	
	private String chassi_moto;
	private String modelo;
	private String placa;
	private Setor setor;
	
	
	
	public MotoDTO(String chassi_moto, String modelo, String placa, Setor setor) {
		super();
		this.chassi_moto = chassi_moto;
		this.modelo = modelo;
		this.placa = placa;
		this.setor = setor;
	}



	public MotoDTO(Moto moto) {
		this.chassi_moto = moto.getChassi_moto();
		this.modelo = moto.getModelo();
		this.placa= moto.getPlaca();
		this.setor = moto.getSetor();
	}
	
	
	
	
	
}
