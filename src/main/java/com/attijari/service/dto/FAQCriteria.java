package com.attijari.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.attijari.domain.FAQ;
import com.attijari.web.rest.FAQResource;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link FAQ} entity. This class is used
 * in {@link FAQResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /faqs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FAQCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter question;

    private StringFilter answer;

    public FAQCriteria() {
    }

    public FAQCriteria(FAQCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.question = other.question == null ? null : other.question.copy();
        this.answer = other.answer == null ? null : other.answer.copy();
    }

    @Override
    public FAQCriteria copy() {
        return new FAQCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getQuestion() {
        return question;
    }

    public void setQuestion(StringFilter question) {
        this.question = question;
    }

    public StringFilter getAnswer() {
        return answer;
    }

    public void setAnswer(StringFilter answer) {
        this.answer = answer;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FAQCriteria that = (FAQCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(question, that.question) &&
            Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        question,
        answer
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FAQCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (question != null ? "question=" + question + ", " : "") +
                (answer != null ? "answer=" + answer + ", " : "") +
            "}";
    }

}
