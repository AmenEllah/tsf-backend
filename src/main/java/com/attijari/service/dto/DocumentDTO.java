package com.attijari.service.dto;

import com.attijari.domain.Document;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link Document} entity.
 */
public class DocumentDTO implements Serializable {

    private Long id;

    private String typeDocument;

    private String nomFichier;

    private String emplacement;

    private String idDossierSigned;

    private Boolean hasSigned;

    private LocalDate dateCreation;

    private LocalDate dateUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public String getIdDossierSigned() {
        return idDossierSigned;
    }

    public void setIdDossierSigned(String idDossierSigned) {
        this.idDossierSigned = idDossierSigned;
    }

    public Boolean isHasSigned() {
        return hasSigned;
    }

    public void setHasSigned(Boolean hasSigned) {
        this.hasSigned = hasSigned;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(LocalDate dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DocumentDTO)) {
            return false;
        }

        return id != null && id.equals(((DocumentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DocumentDTO{" +
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
