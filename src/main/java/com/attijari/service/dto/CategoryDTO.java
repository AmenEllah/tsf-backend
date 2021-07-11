package com.attijari.service.dto;

import com.attijari.domain.Category;

import java.io.Serializable;

/**
 * A DTO for the {@link Category} entity.
 */
public class CategoryDTO implements Serializable {

    private Long id;

    private String categoryNameFR;

    private String categoryNameEN;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryNameFR() {
        return categoryNameFR;
    }

    public void setCategoryNameFR(String categoryNameFR) {
        this.categoryNameFR = categoryNameFR;
    }

    public String getCategoryNameEN() {
        return categoryNameEN;
    }

    public void setCategoryNameEN(String categoryNameEN) {
        this.categoryNameEN = categoryNameEN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategoryDTO)) {
            return false;
        }

        return id != null && id.equals(((CategoryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategoryDTO{" +
            "id=" + getId() +
            ", categoryNameFR='" + getCategoryNameFR() + "'" +
            ", categoryNameEN='" + getCategoryNameEN() + "'" +
            "}";
    }
}
