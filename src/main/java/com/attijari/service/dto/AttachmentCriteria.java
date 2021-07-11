package com.attijari.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.attijari.domain.Attachment;
import com.attijari.web.rest.AttachmentResource;
import io.github.jhipster.service.Criteria;
import com.attijari.domain.enumeration.AttachmentType;
import com.attijari.domain.enumeration.AttachmentStatus;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link Attachment} entity. This class is used
 * in {@link AttachmentResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /attachments?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AttachmentCriteria implements Serializable, Criteria {
    /**
     * Class for filtering AttachmentType
     */
    public static class AttachmentTypeFilter extends Filter<AttachmentType> {

        public AttachmentTypeFilter() {
        }

        public AttachmentTypeFilter(AttachmentTypeFilter filter) {
            super(filter);
        }

        @Override
        public AttachmentTypeFilter copy() {
            return new AttachmentTypeFilter(this);
        }

    }
    /**
     * Class for filtering AttachmentStatus
     */
    public static class AttachmentStatusFilter extends Filter<AttachmentStatus> {

        public AttachmentStatusFilter() {
        }

        public AttachmentStatusFilter(AttachmentStatusFilter filter) {
            super(filter);
        }

        @Override
        public AttachmentStatusFilter copy() {
            return new AttachmentStatusFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter path;

    private AttachmentTypeFilter attachmentType;

    private StringFilter fileName;

    private AttachmentStatusFilter status;

    private LongFilter requestId;

    private StringFilter userAccountLogin;

    public AttachmentCriteria() {
    }

    public AttachmentCriteria(AttachmentCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.path = other.path == null ? null : other.path.copy();
        this.attachmentType = other.attachmentType == null ? null : other.attachmentType.copy();
        this.fileName = other.fileName == null ? null : other.fileName.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.requestId = other.requestId == null ? null : other.requestId.copy();
        this.userAccountLogin = other.userAccountLogin == null ? null : other.userAccountLogin.copy();
    }

    @Override
    public AttachmentCriteria copy() {
        return new AttachmentCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getPath() {
        return path;
    }

    public void setPath(StringFilter path) {
        this.path = path;
    }

    public AttachmentTypeFilter getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(AttachmentTypeFilter attachmentType) {
        this.attachmentType = attachmentType;
    }

    public StringFilter getFileName() {
        return fileName;
    }

    public void setFileName(StringFilter fileName) {
        this.fileName = fileName;
    }

    public AttachmentStatusFilter getStatus() {
        return status;
    }

    public void setStatus(AttachmentStatusFilter status) {
        this.status = status;
    }

    public LongFilter getRequestId() {
        return requestId;
    }

    public void setRequestId(LongFilter requestId) {
        this.requestId = requestId;
    }

    public StringFilter getUserAccountLogin() {
        return userAccountLogin;
    }

    public void setUserAccountLogin(StringFilter userAccountLogin) {
        this.userAccountLogin = userAccountLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AttachmentCriteria that = (AttachmentCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(path, that.path) &&
            Objects.equals(attachmentType, that.attachmentType) &&
            Objects.equals(fileName, that.fileName) &&
            Objects.equals(status, that.status) &&
            Objects.equals(requestId, that.requestId) &&
            Objects.equals(userAccountLogin, that.userAccountLogin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        path,
        attachmentType,
        fileName,
        status,
        requestId,
        userAccountLogin
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AttachmentCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (path != null ? "path=" + path + ", " : "") +
                (attachmentType != null ? "attachmentType=" + attachmentType + ", " : "") +
                (fileName != null ? "fileName=" + fileName + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (requestId != null ? "requestId=" + requestId + ", " : "") +
                (userAccountLogin != null ? "userAccountLogin=" + userAccountLogin + ", " : "") +
            "}";
    }

}
