package heukwu.recruitmentmanagement;

import heukwu.recruitmentmanagement.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static heukwu.recruitmentmanagement.exception.ErrorMessage.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundCompanyException.class)
    public ResponseEntity<?> notFoundCompanyException(NotFoundCompanyException e) {
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(NOT_FOUND_COMPANY.getMessage())
                .build();

        return ResponseEntity.status(errorResponse.code()).body(errorResponse);
    }

    @ExceptionHandler(NotFoundPostException.class)
    public ResponseEntity<?> notFoundPostException(NotFoundPostException e) {
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(NOT_FOUND_POST.getMessage())
                .build();

        return ResponseEntity.status(errorResponse.code()).body(errorResponse);
    }

    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity<?> notFoundUserException(NotFoundUserException e) {
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(NOT_FOUND_USER.getMessage())
                .build();

        return ResponseEntity.status(errorResponse.code()).body(errorResponse);
    }

    @ExceptionHandler(DuplicateApplyException.class)
    public ResponseEntity<?> duplicateApplyException(DuplicateApplyException e) {
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(DUPLICATE_APPLY.getMessage())
                .build();

        return ResponseEntity.status(errorResponse.code()).body(errorResponse);
    }
}
