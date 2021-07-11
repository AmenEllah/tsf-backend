package com.attijari.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class InvalidTokenException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public InvalidTokenException() {
        super(ErrorConstants.INVALID_ACCESS_TOKEN, "Invalid access token", Status.BAD_REQUEST);
    }
}
