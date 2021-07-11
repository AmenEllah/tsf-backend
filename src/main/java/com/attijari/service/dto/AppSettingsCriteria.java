package com.attijari.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.attijari.domain.AppSettings;
import com.attijari.web.rest.AppSettingsResource;
import io.github.jhipster.service.Criteria;
import com.attijari.domain.enumeration.SenderProvider;
import com.attijari.domain.enumeration.SignerProvider;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;

/**
 * Criteria class for the {@link AppSettings} entity. This class is used
 * in {@link AppSettingsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /app-settings?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AppSettingsCriteria implements Serializable, Criteria {
    /**
     * Class for filtering SenderProvider
     */
    public static class SenderProviderFilter extends Filter<SenderProvider> {

        public SenderProviderFilter() {
        }

        public SenderProviderFilter(SenderProviderFilter filter) {
            super(filter);
        }

        @Override
        public SenderProviderFilter copy() {
            return new SenderProviderFilter(this);
        }

    }
    /**
     * Class for filtering SignerProvider
     */
    public static class SignerProviderFilter extends Filter<SignerProvider> {

        public SignerProviderFilter() {
        }

        public SignerProviderFilter(SignerProviderFilter filter) {
            super(filter);
        }

        @Override
        public SignerProviderFilter copy() {
            return new SignerProviderFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private SenderProviderFilter mailSenderProvider;

    private SignerProviderFilter signerProvider;

    public AppSettingsCriteria() {
    }

    public AppSettingsCriteria(AppSettingsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.mailSenderProvider = other.mailSenderProvider == null ? null : other.mailSenderProvider.copy();
        this.signerProvider = other.signerProvider == null ? null : other.signerProvider.copy();
    }

    @Override
    public AppSettingsCriteria copy() {
        return new AppSettingsCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public SenderProviderFilter getMailSenderProvider() {
        return mailSenderProvider;
    }

    public void setMailSenderProvider(SenderProviderFilter mailSenderProvider) {
        this.mailSenderProvider = mailSenderProvider;
    }

    public SignerProviderFilter getSignerProvider() {
        return signerProvider;
    }

    public void setSignerProvider(SignerProviderFilter signerProvider) {
        this.signerProvider = signerProvider;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AppSettingsCriteria that = (AppSettingsCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(mailSenderProvider, that.mailSenderProvider) &&
            Objects.equals(signerProvider, that.signerProvider);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        mailSenderProvider,
        signerProvider
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppSettingsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (mailSenderProvider != null ? "mailSenderProvider=" + mailSenderProvider + ", " : "") +
                (signerProvider != null ? "signerProvider=" + signerProvider + ", " : "") +
            "}";
    }

}
