package com.attijari.service.dto;

import com.attijari.domain.Country;

import java.io.Serializable;

/**
 * A DTO for the {@link Country} entity.
 */
public class CountryDTO implements Serializable {

    private Long id;

    private String nameFR;

    private String nameEN;

    private String code;

    private String flag;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameFR() {
        return nameFR;
    }

    public void setNameFR(String nameFR) {
        this.nameFR = nameFR;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
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
        if (!(o instanceof CountryDTO)) {
            return false;
        }

        return id != null && id.equals(((CountryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CountryDTO{" +
            "id=" + id +
            ", nameFR='" + nameFR + '\'' +
            ", nameEN='" + nameEN + '\'' +
            ", code='" + code + '\'' +
            ", flag='" + flag + '\'' +
            '}';
    }
}
