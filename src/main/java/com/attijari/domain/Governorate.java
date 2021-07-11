package com.attijari.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Governorate.
 */
@Entity
@Table(name = "governorate")
public class Governorate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "governorate", fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    private Set<Municipality> municipalities = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Governorate name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Municipality> getMunicipalities() {
        return municipalities;
    }

    public Governorate municipalities(Set<Municipality> municipalities) {
        this.municipalities = municipalities;
        return this;
    }

    public void setMunicipalities(Set<Municipality> municipalities) {
        this.municipalities = municipalities;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Governorate)) {
            return false;
        }
        return id != null && id.equals(((Governorate) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "Governorate{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", municipalities=" + municipalities +
            '}';
    }
}
