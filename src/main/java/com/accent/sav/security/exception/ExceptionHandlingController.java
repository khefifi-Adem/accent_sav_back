package com.accent.sav.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("Pas trouvé");
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handleResourceAlreadyExistException(ResourceAlreadyExistException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("Existe déjà");
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MustBeLowerThanException.class)
    public ResponseEntity<ExceptionResponse> invalidMinMax(MustBeLowerThanException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("Erreur de validation");
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> invalidInput(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("Erreur de validation");
        response.setErrorMessage("Entrées non valides !");
        response.setErrors(ValidationUtil.fromBindingErrors(result));
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ExceptionResponse> resourceConflict(ResourceConflictException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("Conflit");
        response.setErrorMessage(ex.getMessage());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> resourceNotReadable(HttpMessageNotReadableException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("Illisible");
        response.setErrorMessage(ex.getMessage());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDataException.class)
    public ResponseEntity<ExceptionResponse> notDataBetweenThisTwoDate(NoDataException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("Conflit");
        response.setErrorMessage("Pas de données entre date de début et date de fin !");
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> MethodArgumentNotValid(HttpRequestMethodNotSupportedException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("Méthode non autorisée");
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponse> MethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("Incompatibilité de type");
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> InternalServerError(Exception ex) {
        ex.printStackTrace();
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("Erreur");
        response.setErrorMessage("Veuillez contacter votre administrateur !");
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}