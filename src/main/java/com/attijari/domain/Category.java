package com.attijari.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A Category.
 */
@Entity
@Table(name = "category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "category_name_fr")
    private String categoryNameFR;

    @Column(name = "category_name_en")
    private String categoryNameEN;


    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryNameFR() {
        return categoryNameFR;
    }

    public Category categoryNameFR(String categoryNameFR) {
        this.categoryNameFR = categoryNameFR;
        return this;
    }

    public void setCategoryNameFR(String categoryNameFR) {
        this.categoryNameFR = categoryNameFR;
    }

    public String getCategoryNameEN() {
        return categoryNameEN;
    }

    public Category categoryNameEN(String categoryNameEN) {
        this.categoryNameEN = categoryNameEN;
        return this;
    }

    public void setCategoryNameEN(String categoryNameEN) {
        this.categoryNameEN = categoryNameEN;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        return id != null && id.equals(((Category) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Category{" +
            "id=" + getId() +
            ", categoryNameFR='" + getCategoryNameFR() + "'" +
            ", categoryNameEN='" + getCategoryNameEN() + "'" +
            "}";
    }
}
