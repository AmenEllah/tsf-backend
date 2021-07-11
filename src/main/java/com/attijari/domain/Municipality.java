package com.attijari.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Municipality.
 */
@Entity
@Table(name = "municipality")
public class Municipality implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "municipality", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Agency> agencies = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Governorate governorate;

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

    public Municipality name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Agency> getAgencies() {
        return agencies;
    }

    public Municipality agencies(Set<Agency> agencies) {
        this.agencies = agencies;
        return this;
    }

    public Municipality addAgency(Agency agency) {
        this.agencies.add(agency);
        agency.setMunicipality(this);
        return this;
    }

    public void setAgencies(Set<Agency> agencies) {
        this.agencies = agencies;
    }

    public Governorate getGovernorate() {
        return governorate;
    }

    public Municipality governorate(Governorate governorate) {
        this.governorate = governorate;
        return this;
    }

    public void setGovernorate(Governorate governorate) {
        this.governorate = governorate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Municipality)) {
            return false;
        }
        return id != null && id.equals(((Municipality) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Municipality{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", agencies=" + agencies +
            ", governorate=" + governorate +
            '}';
    }
}
