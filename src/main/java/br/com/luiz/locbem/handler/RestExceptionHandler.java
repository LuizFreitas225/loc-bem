package br.com.luiz.locbem.handler;

import br.com.luiz.locbem.constant.ErrorMessage;
import br.com.luiz.locbem.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserIsDeletedException.class)
    public ResponseEntity<Object> userIsDeletedException(UserIsDeletedException exception) {
        return new ResponseEntity(new ExceptionDetails(ErrorMessage.DELETED_STATUS, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserIsInactiveException.class)
    public ResponseEntity<Object> userIsInactive(UserIsDeletedException exception) {
        return new ResponseEntity(new ExceptionDetails(ErrorMessage.INACTIVE_STATUS, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OfertaNotFoundException.class)
    public ResponseEntity<Object> OfertaNotFoundException(OfertaNotFoundException exception) {
        return new ResponseEntity(new ExceptionDetails(ErrorMessage.OFERTA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundException(UserIsDeletedException exception) {
        return new ResponseEntity(new ExceptionDetails(ErrorMessage.USER_NOT_FOUND, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailInUseException.class)
    public ResponseEntity<Object> emailInUseException(EmailInUseException exception) {
        return new ResponseEntity(new ExceptionDetails(ErrorMessage.EMAIL_IN_USE, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShortPasswordException.class)
    public ResponseEntity<Object> invalidPasswordException(ShortPasswordException exception) {
        return new ResponseEntity(new ExceptionDetails(ErrorMessage.SHORT_PASSWORD, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PersonRegistrationInUseException.class)
    public ResponseEntity<Object> PersonRegistrationInUse(PersonRegistrationInUseException exception) {
        return new ResponseEntity(new ExceptionDetails(ErrorMessage.PERSON_REGISTRATION_IN_USE, HttpStatus.BAD_REQUEST.value()), HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(PersonRegistrationInvalidException.class)
    public ResponseEntity<Object> PersonRegistrationInUse(PersonRegistrationInvalidException exception) {
        return new ResponseEntity(new ExceptionDetails(ErrorMessage.PERSON_REGISTRATION_INVALID, HttpStatus.BAD_REQUEST.value()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> ForbiddenException(ForbiddenException exception) {
        return new ResponseEntity(new ExceptionDetails(ErrorMessage.ACCESS_DENIED, HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MapBoxException.class)
    public ResponseEntity<Object> MapBoxException(MapBoxException exception) {
        return new ResponseEntity(new ExceptionDetails(ErrorMessage.MAPBOX_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errors = ex.getBindingResult().getAllErrors().stream().map(
                error -> error.getDefaultMessage()).collect(Collectors.toList()).toString();
        return new ResponseEntity(new ExceptionDetails(errors, status.value()), status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ExceptionDetails("Alguma informação não foi informada como esperado, verifique os dados enviados e tente novamente.",
                status.value()), status);
    }

}
