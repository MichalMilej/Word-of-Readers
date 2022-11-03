package pl.milej.michal.wordofreaders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class RelationAlreadySetException extends RuntimeException{

    public RelationAlreadySetException(final String message) {
        super(message, null);
    }
}
