package com.lugiatracker.dto;

import org.springframework.hateoas.RepresentationModel;

import com.lugiatracker.model.Moto;
import com.lugiatracker.model.Setor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data
public class MotoDTO extends RepresentationModel<Moto> {

	

    @NotBlank(message = "O chassi é obrigatório")
	private String chassi_moto;
    
    @NotBlank(message = "O modelo é obrigatório")
    @Size(min = 2, max = 30, message = "O modelo deve ter entre 2 e 30 caracteres")
	private String modelo;
    
    
    @Size(min = 7, max = 8, message = "A placa deve ter entre 7 e 8 caracteres")
	private String placa;
	
	
    @NotNull(message = "O setor é obrigatório")
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
