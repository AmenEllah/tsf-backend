package com.attijari.domain;


import org.springframework.lang.Nullable;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Document.
 */
@Entity
@Table(name = "document")
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;
    @Nullable
    @Column(name = "type_document")
    private String typeDocument;
    @Nullable
    @Column(name = "nom_fichier")
    private String nomFichier;
    @Nullable
    @Column(name = "emplacement")
    private String emplacement;
    @Nullable
    @Column(name = "id_dossier_signed")
    private String idDossierSigned;
    @Nullable
    @Column(name = "has_signed")
    private Boolean hasSigned;
    @Nullable
    @Column(name = "date_creation")
    private LocalDate dateCreation;
    @Nullable
    @Column(name = "date_update")
    private LocalDate dateUpdate;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeDocument() {
        return typeDocument;
    }

    public Document typeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
        return this;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public Document nomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
        return this;
    }

    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public Document emplacement(String emplacement) {
        this.emplacement = emplacement;
        return this;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public String getIdDossierSigned() {
        return idDossierSigned;
    }

    public Document idDossierSigned(String idDossierSigned) {
        this.idDossierSigned = idDossierSigned;
        return this;
    }

    public void setIdDossierSigned(String idDossierSigned) {
        this.idDossierSigned = idDossierSigned;
    }

    public Boolean isHasSigned() {
        return hasSigned;
    }

    public Document hasSigned(Boolean hasSigned) {
        this.hasSigned = hasSigned;
        return this;
    }

    public void setHasSigned(Boolean hasSigned) {
        this.hasSigned = hasSigned;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public Document dateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateUpdate() {
        return dateUpdate;
    }

    public Document dateUpdate(LocalDate dateUpdate) {
        this.dateUpdate = dateUpdate;
        return this;
    }

    public void setDateUpdate(LocalDate dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Document)) {
            return false;
        }
        return id != null && id.equals(((Document) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Document{" +
            "id=" + getId() +
            ", typeDocument='" + getTypeDocument() + "'" +
            ", nomFichier='" + getNomFichier() + "'" +
            ", emplacement='" + getEmplacement() + "'" +
            ", idDossierSigned='" + getIdDossierSigned() + "'" +
            ", hasSigned='" + isHasSigned() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", dateUpdate='" + getDateUpdate() + "'" +
            "}";
    }
}
