package io.github.soumikuxd.pagerhazelcastdemo.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class BadInputException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String errorCode;

    public BadInputException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}