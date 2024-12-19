package pl.milej.michal.worldofreaders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredVariablesNotSetException extends RuntimeException {

    public RequiredVariablesNotSetException(final String message) {
        super(message, null);
    }
}
