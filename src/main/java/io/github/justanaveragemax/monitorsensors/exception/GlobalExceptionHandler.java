package io.github.justanaveragemax.monitorsensors.exception;

import io.github.justanaveragemax.monitorsensors.dto.FieldViolationDto;
import io.github.justanaveragemax.monitorsensors.dto.resposnse.ApplicationErrorResponse;
import io.github.justanaveragemax.monitorsensors.dto.resposnse.ValidationErrorResponse;
import jakarta.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<ApplicationErrorResponse> handleInternalError(Exception ex){
    return handleGenericException(HttpStatus.INTERNAL_SERVER_ERROR, ex);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ValidationErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex){
    List<FieldViolationDto> violations = ex.getBindingResult().getFieldErrors().stream()
        .map(error -> new FieldViolationDto(error.getField(), error.getDefaultMessage()))
        .toList();

    return ResponseEntity.badRequest().body(new ValidationErrorResponse(violations));
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ValidationErrorResponse> handleConstraintViolations(ConstraintViolationException ex){
    List<FieldViolationDto> violations = ex.getConstraintViolations().stream()
        .map(error -> new FieldViolationDto(error.getPropertyPath().toString(), error.getMessage()))
        .toList();

    return ResponseEntity.badRequest().body(new ValidationErrorResponse(violations));
  }

  private ResponseEntity<ApplicationErrorResponse> handleGenericException(HttpStatus httpStatus, Throwable ex){
    ApplicationErrorResponse rs = ApplicationErrorResponse.builder()
        .code(httpStatus.value())
        .timestamp(LocalDateTime.now().toString())
        .description(ex.getMessage())
        .build();

    return ResponseEntity.status(httpStatus).body(rs);
  }

}
