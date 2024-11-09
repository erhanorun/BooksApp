//package com.bookstore.backend.exceptions;
//
//import com.bookstore.backend.response.ErrorResponse;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.servlet.resource.NoResourceFoundException;
//
//import java.time.LocalDateTime;
//import java.util.*;
//
//@ControllerAdvice
//public class GlobalExceptionHandler extends RuntimeException {
//
//
//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<?> handleNullPointerException(Exception e) {
//        ErrorResponse error = ErrorResponse.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                .message(e.getMessage()).timestamp(LocalDateTime.now()).build();
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<?> handleIllegalArgumentException(Exception e) {
//        ErrorResponse error = ErrorResponse.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                .message(e.getMessage()).timestamp(LocalDateTime.now()).build();
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }
//
//    @ExceptionHandler(MissingServletRequestParameterException.class)
//    public ResponseEntity<?> handleMissingParameterException(Exception e) {
//        ErrorResponse error = ErrorResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value())
//                .message(e.getMessage()).timestamp(LocalDateTime.now()).build();
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(NoResourceFoundException.class)
//    public ResponseEntity<?> handleNoResourceFoundException(NoResourceFoundException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGeneralException(Exception ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    private Map<String, List<String>> getErrorsMap(List<String> errors) {
//        Map<String, List<String>> errorResponse = new HashMap<>();
//        errorResponse.put("errors", errors);
//        return errorResponse;
//    }
//
//    @ExceptionHandler(ResourceAlreadyExistsException.class)
//    public ResponseEntity<ErrorResponse> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
//        ErrorResponse errorResponse = new ErrorResponse(
//                ex.getMessage(),
//                HttpStatus.CONFLICT.value(),
//                LocalDateTime.now()
//        );
//        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
//    }
//
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<Map<String, List<String>>> handleNotFoundException(ResourceNotFoundException ex) {
//        List<String> errors = Collections.singletonList(ex.getMessage());
//        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    public final ResponseEntity<Map<String, List<String>>> handleRuntimeExceptions(RuntimeException ex) {
//        List<String> errors = Collections.singletonList(ex.getMessage());
//        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
