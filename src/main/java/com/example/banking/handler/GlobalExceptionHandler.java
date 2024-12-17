package com.example.banking.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.banking.helpers.ObjectValidationException;
import com.example.banking.helpers.OperationNonPermittedException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(ObjectValidationException.class)
   ResponseEntity<ExceptionRepresentation> handlerException(ObjectValidationException exception){
       ExceptionRepresentation representation = 
                  ExceptionRepresentation.
                  builder()
                  .errorMessage("Object Not Valid exception")
                  .errorSource(exception.getViolationSource())
                  .validationError(exception.getViolations())
                  .build();

        return ResponseEntity
        .status(HttpStatus.BAD_REQUEST) // 400
        .body(representation)
        ;

   }


   @ExceptionHandler(EntityNotFoundException.class)
   public ResponseEntity<ExceptionRepresentation> handlerException(EntityNotFoundException exception){
    ExceptionRepresentation representation = 
        ExceptionRepresentation.
        builder()
        .errorMessage(exception.getMessage())
        .build();

        return ResponseEntity
        .status(HttpStatus.NOT_FOUND) // 404
        .body(representation)
        ;
   }




   @ExceptionHandler(OperationNonPermittedException.class)
   public ResponseEntity<ExceptionRepresentation> handlerException(OperationNonPermittedException exception){
    ExceptionRepresentation representation = 
        ExceptionRepresentation.
        builder()
        .errorMessage(exception.getMessage())
        .build();

        return ResponseEntity
        .status(HttpStatus.NOT_ACCEPTABLE) // 406
        .body(representation)
        ;
   }



}
