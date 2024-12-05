package com.alucontrol.saleservice.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle custom resource not found exception
    @ExceptionHandler(SaleNotFoundException.class)
    public ResponseEntity<ProblemDetails> handleResourceNotFoundException(SaleNotFoundException ex){

        ProblemDetails problemDetails = new ProblemDetails(
                HttpStatus.NOT_FOUND.toString(),
                "Sale Not Found",
                ex.getMessage(),
                "A venda solicitada não foi encontrado",
                UUID.randomUUID()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetails);
    }

    // Handle argument validation error exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetails>  handleValidationException(MethodArgumentNotValidException ex) {

        ProblemDetails problemDetails = new ProblemDetails(
                HttpStatus.BAD_REQUEST.toString(),
                "Validation Error",
                "Um ou mais campos não são válidos",
                ex.getBindingResult().getAllErrors().toString(),
                UUID.randomUUID()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetails);
    }

    //Handle exception to restriction error in database
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ProblemDetails> handleConstraintViolationException(ConstraintViolationException ex){

        ProblemDetails problemDetails = new ProblemDetails(
                HttpStatus.BAD_REQUEST.toString(),
                "Constraint Violation",
                "Violação de restrição no banco de dados",
                ex.getMessage(),
                UUID.randomUUID()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetails);
    }

    //Handling generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetails> handleException(Exception ex){

        String stackTrace = Arrays.toString(ex.getStackTrace());

        ProblemDetails problemDetails = new ProblemDetails(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                "Internal Server Error",
                "Ocorreu um erro inesperado no servidor",
                ex.getMessage(),
                UUID.randomUUID()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problemDetails);
    }

    //Handle exceptions to data access
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ProblemDetails> handleDataAccessException(DataAccessException ex){

        ProblemDetails problemDetails = new ProblemDetails(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                "Data Access Error",
                "Erro ao acessar o banco de dados",
                ex.getMessage(),
                UUID.randomUUID()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problemDetails);
    }


    //Deals with timeout of communication between microservices
    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<ProblemDetails> handleTimeoutException(TimeoutException ex){

        ProblemDetails problemDetails = new ProblemDetails(
                HttpStatus.REQUEST_TIMEOUT.toString(),
                "Request Timeout",
                "O tempo de resposta do microserviço de destino expirou",
                ex.getMessage(),
                UUID.randomUUID()
        );
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(problemDetails);
    }

    // This specifically handle the insufficient stock scenario
    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ProblemDetails> handleInsufficientStockException(InsufficientStockException ex){
        ProblemDetails problemDetails = new ProblemDetails(
                HttpStatus.UNPROCESSABLE_ENTITY.toString(),
                "Insufficient Stock",
                "A quantidade solicida não esta disponível em estoque",
                ex.getMessage(),
                UUID.randomUUID()
        );
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(problemDetails);
    }
}
