package com.lugiatracker.dto;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lugiatracker.model.Moto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class MotoDTO extends RepresentationModel<MotoDTO> {
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id; 

    @NotBlank(message = "O chassi é obrigatório")
    @Pattern(regexp = "^[A-HJ-NPR-Z0-9]{17}$", message = "Formato de chassi inválido. Deve ter 17 caracteres alfanuméricos.")
    @JsonProperty("chassimoto")
    private String chassimoto;

    @NotBlank(message = "O modelo é obrigatório")
    @Size(min = 2, max = 30, message = "O modelo deve ter entre 2 e 30 caracteres")
    private String modelo;

    @NotBlank(message = "A placa é obrigatória")
    @Pattern(regexp = "^[A-Z]{3}-[0-9]{4}$", message = "Formato da placa inválido. Exemplo válido: ABC-1234")
    private String placa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChassimoto() {
		return chassimoto;
	}

	public void setChassimoto(String chassimoto) {
		this.chassimoto = chassimoto;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public MotoDTO(Long id,
			@NotBlank(message = "O chassi é obrigatório") @Pattern(regexp = "^[A-HJ-NPR-Z0-9]{17}$", message = "Formato de chassi inválido. Deve ter 17 caracteres alfanuméricos.") String chassimoto,
			@NotBlank(message = "O modelo é obrigatório") @Size(min = 2, max = 30, message = "O modelo deve ter entre 2 e 30 caracteres") String modelo,
			@NotBlank(message = "A placa é obrigatória") @Pattern(regexp = "^[A-Z]{3}-[0-9]{4}$", message = "Formato da placa inválido. Exemplo válido: ABC-1234") String placa) {
		super();
		this.id = id;
		this.chassimoto = chassimoto;
		this.modelo = modelo;
		this.placa = placa;
	}

	public MotoDTO() {
		super();
	}

	

	@Override
	public String toString() {
		return "MotoDTO [id=" + id + ", chassimoto=" + chassimoto + ", modelo=" + modelo + ", placa=" + placa + "]";
	}

	public MotoDTO(Moto moto) {
	    this.id = moto.getId();
	    this.chassimoto = moto.getChassimoto();
	    this.modelo = moto.getModelo();
	    this.placa = moto.getPlaca();
	}
   
}
