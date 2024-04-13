package one.digitalinnovation.gof.core.config.exception;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

  @ExceptionHandler(value = { NotFoundException.class })
  protected ResponseEntity<Object> handleNotFoundException(RuntimeException ex) {
    ApplicationError error = new ApplicationError(HttpStatus.NOT_FOUND, ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  @ExceptionHandler(value = { HttpMessageNotReadableException.class })
  protected ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
    String message = Objects.requireNonNullElse(ex.getRootCause(), ex).getMessage();
    ApplicationError error = new ApplicationError(HttpStatus.BAD_REQUEST, message);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler(value = { BadRequestException.class, RuntimeException.class })
  protected ResponseEntity<Object> handleBadRequest(RuntimeException ex) {
    ApplicationError error = new ApplicationError(HttpStatus.BAD_REQUEST, ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler(value = { Exception.class })
  protected ResponseEntity<Object> handleInternal(Exception ex) {
    ApplicationError error = new ApplicationError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }

}
