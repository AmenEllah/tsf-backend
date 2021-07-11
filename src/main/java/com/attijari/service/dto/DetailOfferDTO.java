package com.attijari.service.dto;

import com.attijari.domain.DetailOffer;

import java.io.Serializable;

/**
 * A DTO for the {@link DetailOffer} entity.
 */
public class DetailOfferDTO implements Serializable {

    private Long id;

    private String description;

    private String descriptionEn;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetailOfferDTO)) {
            return false;
        }

        return id != null && id.equals(((DetailOfferDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetailOfferDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }
}
