package com.attijari.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.attijari.domain.Document;
import com.attijari.web.rest.DocumentResource;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link Document} entity. This class is used
 * in {@link DocumentResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /documents?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DocumentCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter typeDocument;

    private StringFilter nomFichier;

    private StringFilter emplacement;

    private StringFilter idDossierSigned;

    private BooleanFilter hasSigned;

    private LocalDateFilter dateCreation;

    private LocalDateFilter dateUpdate;

    private LongFilter requestId;

    public DocumentCriteria() {
    }

    public DocumentCriteria(DocumentCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.typeDocument = other.typeDocument == null ? null : other.typeDocument.copy();
        this.nomFichier = other.nomFichier == null ? null : other.nomFichier.copy();
        this.emplacement = other.emplacement == null ? null : other.emplacement.copy();
        this.idDossierSigned = other.idDossierSigned == null ? null : other.idDossierSigned.copy();
        this.hasSigned = other.hasSigned == null ? null : other.hasSigned.copy();
        this.dateCreation = other.dateCreation == null ? null : other.dateCreation.copy();
        this.dateUpdate = other.dateUpdate == null ? null : other.dateUpdate.copy();
        this.requestId = other.requestId == null ? null : other.requestId.copy();
    }

    @Override
    public DocumentCriteria copy() {
        return new DocumentCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(StringFilter typeDocument) {
        this.typeDocument = typeDocument;
    }

    public StringFilter getNomFichier() {
        return nomFichier;
    }

    public void setNomFichier(StringFilter nomFichier) {
        this.nomFichier = nomFichier;
    }

    public StringFilter getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(StringFilter emplacement) {
        this.emplacement = emplacement;
    }

    public StringFilter getIdDossierSigned() {
        return idDossierSigned;
    }

    public void setIdDossierSigned(StringFilter idDossierSigned) {
        this.idDossierSigned = idDossierSigned;
    }

    public BooleanFilter getHasSigned() {
        return hasSigned;
    }

    public void setHasSigned(BooleanFilter hasSigned) {
        this.hasSigned = hasSigned;
    }

    public LocalDateFilter getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateFilter dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateFilter getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(LocalDateFilter dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public LongFilter getRequestId() {
        return requestId;
    }

    public void setRequestId(LongFilter requestId) {
        this.requestId = requestId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DocumentCriteria that = (DocumentCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(typeDocument, that.typeDocument) &&
            Objects.equals(nomFichier, that.nomFichier) &&
            Objects.equals(emplacement, that.emplacement) &&
            Objects.equals(idDossierSigned, that.idDossierSigned) &&
            Objects.equals(hasSigned, that.hasSigned) &&
            Objects.equals(dateCreation, that.dateCreation) &&
            Objects.equals(dateUpdate, that.dateUpdate) &&
            Objects.equals(requestId, that.requestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        typeDocument,
        nomFichier,
        emplacement,
        idDossierSigned,
        hasSigned,
        dateCreation,
        dateUpdate,
        requestId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DocumentCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (typeDocument != null ? "typeDocument=" + typeDocument + ", " : "") +
                (nomFichier != null ? "nomFichier=" + nomFichier + ", " : "") +
                (emplacement != null ? "emplacement=" + emplacement + ", " : "") +
                (idDossierSigned != null ? "idDossierSigned=" + idDossierSigned + ", " : "") +
                (hasSigned != null ? "hasSigned=" + hasSigned + ", " : "") +
                (dateCreation != null ? "dateCreation=" + dateCreation + ", " : "") +
                (dateUpdate != null ? "dateUpdate=" + dateUpdate + ", " : "") +
                (requestId != null ? "requestId=" + requestId + ", " : "") +
            "}";
    }

}
