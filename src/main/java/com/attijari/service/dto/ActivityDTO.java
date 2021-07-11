package com.attijari.service.dto;

import com.attijari.domain.Activity;

import java.io.Serializable;

/**
 * A DTO for the {@link Activity} entity.
 */
public class ActivityDTO implements Serializable {

    private Long id;

    private String activityNameFR;

    private String activityNameEN;

    private String code;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityNameFR() {
        return activityNameFR;
    }

    public void setActivityNameFR(String activityNameFR) {
        this.activityNameFR = activityNameFR;
    }

    public String getActivityNameEN() {
        return activityNameEN;
    }

    public void setActivityNameEN(String activityNameEN) {
        this.activityNameEN = activityNameEN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActivityDTO)) {
            return false;
        }

        return id != null && id.equals(((ActivityDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ActivityDTO{" +
            "id=" + getId() +
            ", activityNameFR='" + getActivityNameFR() + "'" +
            ", activityNameEN='" + getActivityNameEN() + "'" +
            "}";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
