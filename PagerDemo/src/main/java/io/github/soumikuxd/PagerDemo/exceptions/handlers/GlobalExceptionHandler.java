package io.github.soumikuxd.PagerDemo.exceptions.handlers;

import io.github.soumikuxd.PagerDemo.exceptions.BadDataException;
import io.github.soumikuxd.PagerDemo.exceptions.BadInputException;
import io.github.soumikuxd.PagerDemo.exceptions.ResourceNotFoundException;
import io.github.soumikuxd.PagerDemo.exceptions.models.ErrorDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        final String errMsg = exception.getMessage();
        final String errCode = exception.getErrorCode();
        logger.error(errMsg);
        logger.info("errCode: " + errCode);
        ErrorDetail errorDetail = new ErrorDetail(new Date(), exception.getErrorCode(), errMsg, request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadInputException.class)
    public ResponseEntity<?> badInputException(BadInputException exception, WebRequest request) {
        final String errMsg = exception.getMessage();
        final String errCode = exception.getErrorCode();
        logger.error(errMsg);
        logger.info("errCode: " + errCode);
        ErrorDetail errorDetail = new ErrorDetail(new Date(), exception.getErrorCode(), errMsg, request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadDataException.class)
    public ResponseEntity<?> badDataException(BadDataException exception, WebRequest request) {
        final String errMsg = exception.getMessage();
        logger.error(errMsg);
        ErrorDetail errorDetail = new ErrorDetail(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), errMsg, request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception exception, WebRequest request) {
        final String errMsg = exception.getMessage();
        logger.error(errMsg);

        if (exception.getClass() == MissingServletRequestParameterException.class) {
            ErrorDetail errorDetail = new ErrorDetail(new Date(), HttpStatus.BAD_REQUEST.toString(), errMsg, request.getDescription(false));
            return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
        } else {
            ErrorDetail errorDetail = new ErrorDetail(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), errMsg, request.getDescription(false));
            return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
