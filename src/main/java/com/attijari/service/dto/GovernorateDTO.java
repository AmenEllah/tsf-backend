package com.attijari.service.dto;


import com.attijari.domain.Governorate;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link Governorate} entity.
 */
public class GovernorateDTO implements Serializable {

    private Long id;

    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GovernorateDTO that = (GovernorateDTO) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GovernorateDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
