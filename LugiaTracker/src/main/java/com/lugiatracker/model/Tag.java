package com.lugiatracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "tag")
public class Tag {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoTag;

    @NotEmpty(message = "Não é permitido uma tag sem status.")
    @Column(nullable = false)
    private String status;

    @NotBlank(message = "O chassi é obrigatório")
    @Pattern(
        regexp = "^[A-HJ-NPR-Z0-9]{17}$",
        message = "Formato de chassi inválido. Deve ter 17 caracteres alfanuméricos, sem I, O ou Q."
    )
    @Column(name = "chassi_moto", length = 17, nullable = false)
    private String chassi;

    // getters e setters
    public Long getCodigoTag() {
        return codigoTag;
    }

    public void setCodigoTag(Long codigoTag) {
        this.codigoTag = codigoTag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }
}
