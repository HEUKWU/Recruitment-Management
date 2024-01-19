package heukwu.recruitmentmanagement;

import heukwu.recruitmentmanagement.exception.BusinessException;
import heukwu.recruitmentmanagement.exception.ErrorResponse;
import heukwu.recruitmentmanagement.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException e) {
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(errorResponse.code()).body(errorResponse);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> BusinessException(BusinessException e) {
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(errorResponse.code()).body(errorResponse);
    }
}
