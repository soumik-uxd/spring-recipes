package io.github.soumikuxd.pagerhazelcastdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class BadDataException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public BadDataException(String message) {
        super(message);
    }
}
