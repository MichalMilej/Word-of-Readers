package pl.milej.michal.wordofreaders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BadServerRequestException extends RuntimeException {

    public BadServerRequestException(final String message) {
        super(message, null);
    }
}
