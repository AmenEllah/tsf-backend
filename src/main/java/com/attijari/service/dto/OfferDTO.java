package com.attijari.service.dto;

import com.attijari.domain.Offer;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link Offer} entity.
 */
public class OfferDTO implements Serializable {

    private Long id;

    private String name;

    private Double price;

    private String url;

    private String code;


    private Set<DetailOfferDTO> detailOffers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<DetailOfferDTO> getDetailOffers() {
        return detailOffers;
    }

    public void setDetailOffers(Set<DetailOfferDTO> detailOffers) {
        this.detailOffers = detailOffers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OfferDTO)) {
            return false;
        }

        return id != null && id.equals(((OfferDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OfferDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", price=" + getPrice() +
            ", url='" + getUrl() + "'" +
            ", detailOffers='" + getDetailOffers() + "'" +
            "}";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
