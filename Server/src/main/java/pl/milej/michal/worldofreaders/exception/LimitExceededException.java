package pl.milej.michal.worldofreaders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class LimitExceededException extends RuntimeException{

    public LimitExceededException(final String message) {
        super(message, null);
    }
}
