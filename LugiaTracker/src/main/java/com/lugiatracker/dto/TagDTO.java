package com.lugiatracker.dto;

import org.springframework.hateoas.RepresentationModel;

public class TagDTO extends RepresentationModel<TagDTO> {
    private Long codigoTag;
    private String status;
    private String chassi;

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
