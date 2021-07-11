package com.attijari.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A Activity.
 */
@Entity
@Table(name = "activity")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "activity_name_fr")
    private String activityNameFR;

    @Column(name = "activity_name_en")
    private String activityNameEN;

    @Column(name = "code")
    private String code;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityNameFR() {
        return activityNameFR;
    }

    public Activity activityNameFR(String activityNameFR) {
        this.activityNameFR = activityNameFR;
        return this;
    }

    public void setActivityNameFR(String activityNameFR) {
        this.activityNameFR = activityNameFR;
    }

    public String getActivityNameEN() {
        return activityNameEN;
    }

    public Activity activityNameEN(String activityNameEN) {
        this.activityNameEN = activityNameEN;
        return this;
    }

    public void setActivityNameEN(String activityNameEN) {
        this.activityNameEN = activityNameEN;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Activity)) {
            return false;
        }
        return id != null && id.equals(((Activity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Activity{" +
            "id=" + id +
            ", activityNameFR='" + activityNameFR + '\'' +
            ", activityNameEN='" + activityNameEN + '\'' +
            '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
