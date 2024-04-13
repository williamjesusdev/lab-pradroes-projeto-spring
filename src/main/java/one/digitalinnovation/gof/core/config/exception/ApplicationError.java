package one.digitalinnovation.gof.core.config.exception;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;

public record ApplicationError(Integer status, String error, String message, Timestamp timestamp) {

    public ApplicationError(HttpStatus status, String message) {
        this(status.value(), status.getReasonPhrase(), message, new Timestamp(System.currentTimeMillis()));
    }

}
