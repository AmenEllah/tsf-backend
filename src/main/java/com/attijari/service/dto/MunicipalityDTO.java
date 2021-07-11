package com.attijari.service.dto;


import com.attijari.domain.Municipality;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link Municipality} entity.
 */
public class MunicipalityDTO implements Serializable {

    private Long id;

    private String name;

    private Long governorateId;

    private GovernorateDTO governorate;

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

    public Long getGovernorateId() {
        return governorateId;
    }

    public void setGovernorateId(Long governorateId) {
        this.governorateId = governorateId;
    }


    public GovernorateDTO getGovernorate() {
        return governorate;
    }

    public void setGovernorate(GovernorateDTO governorate) {
        this.governorate = governorate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MunicipalityDTO that = (MunicipalityDTO) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(governorateId, that.governorateId) &&
            Objects.equals(governorate, that.governorate);
    }

    @Override
    public String toString() {
        return "MunicipalityDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", governorateId=" + governorateId +
            ", governorate=" + governorate +
            '}';
    }

    @Override
    public int hashCode() {
        return 31;
    }


}
