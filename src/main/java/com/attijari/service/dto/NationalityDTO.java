package com.attijari.service.dto;

import com.attijari.domain.Nationality;

import java.io.Serializable;

/**
 * A DTO for the {@link Nationality} entity.
 */
public class NationalityDTO implements Serializable {

    private Long id;

    private String libelleFR;

    private String libelleEN;

    private String code;

    private String flag;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleFR() {
        return libelleFR;
    }

    public void setLibelleFR(String libelleFR) {
        this.libelleFR = libelleFR;
    }

    public String getLibelleEN() {
        return libelleEN;
    }

    public void setLibelleEN(String libelleEN) {
        this.libelleEN = libelleEN;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NationalityDTO)) {
            return false;
        }

        return id != null && id.equals(((NationalityDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NationalityDTO{" +
            "id=" + getId() +
            ", libelleFR='" + getLibelleFR() + "'" +
            ", libelleEN='" + getLibelleEN() + "'" +
            ", code='" + getCode() + "'" +
            ", flag='" + getFlag() + "'" +
            "}";
    }
}
