package com.attijari.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import com.attijari.domain.enumeration.DocStatus;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link com.attijari.domain.ReqDocUpload} entity. This class is used
 * in {@link com.attijari.web.rest.ReqDocUploadResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /req-doc-uploads?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ReqDocUploadCriteria implements Serializable, Criteria {
    /**
     * Class for filtering DocStatus
     */
    public static class DocStatusFilter extends Filter<DocStatus> {

        public DocStatusFilter() {
        }

        public DocStatusFilter(DocStatusFilter filter) {
            super(filter);
        }

        @Override
        public DocStatusFilter copy() {
            return new DocStatusFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter pathDoc;

    private StringFilter typeDoc;

    private LocalDateFilter uploadedAt;

    private InstantFilter updatedAt;

    private DocStatusFilter docStatus;

    private LongFilter requestId;

    public ReqDocUploadCriteria() {
    }

    public ReqDocUploadCriteria(ReqDocUploadCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.pathDoc = other.pathDoc == null ? null : other.pathDoc.copy();
        this.typeDoc = other.typeDoc == null ? null : other.typeDoc.copy();
        this.uploadedAt = other.uploadedAt == null ? null : other.uploadedAt.copy();
        this.updatedAt = other.updatedAt == null ? null : other.updatedAt.copy();
        this.docStatus = other.docStatus == null ? null : other.docStatus.copy();
        this.requestId = other.requestId == null ? null : other.requestId.copy();
    }

    @Override
    public ReqDocUploadCriteria copy() {
        return new ReqDocUploadCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getPathDoc() {
        return pathDoc;
    }

    public void setPathDoc(StringFilter pathDoc) {
        this.pathDoc = pathDoc;
    }

    public StringFilter getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(StringFilter typeDoc) {
        this.typeDoc = typeDoc;
    }

    public LocalDateFilter getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateFilter uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public InstantFilter getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(InstantFilter updatedAt) {
        this.updatedAt = updatedAt;
    }

    public DocStatusFilter getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(DocStatusFilter docStatus) {
        this.docStatus = docStatus;
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
        final ReqDocUploadCriteria that = (ReqDocUploadCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(pathDoc, that.pathDoc) &&
            Objects.equals(typeDoc, that.typeDoc) &&
            Objects.equals(uploadedAt, that.uploadedAt) &&
            Objects.equals(updatedAt, that.updatedAt) &&
            Objects.equals(docStatus, that.docStatus) &&
            Objects.equals(requestId, that.requestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        pathDoc,
        typeDoc,
        uploadedAt,
        updatedAt,
        docStatus,
        requestId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReqDocUploadCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (pathDoc != null ? "pathDoc=" + pathDoc + ", " : "") +
                (typeDoc != null ? "typeDoc=" + typeDoc + ", " : "") +
                (uploadedAt != null ? "uploadedAt=" + uploadedAt + ", " : "") +
                (updatedAt != null ? "updatedAt=" + updatedAt + ", " : "") +
                (docStatus != null ? "docStatus=" + docStatus + ", " : "") +
                (requestId != null ? "requestId=" + requestId + ", " : "") +
            "}";
    }

}
