package br.com.fiap.aocs.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import br.com.fiap.aocs.exceptions.RestNotFoundException;
import br.com.fiap.aocs.models.RestValidationError;
import br.com.fiap.aocs.models.ReturnAPI;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestExceptionHandler {

    Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<RestValidationError>> MethodArgumentNotValidHandler(MethodArgumentNotValidException e) {// escre

        log.error("Erro de validação");

        return ResponseEntity.badRequest().body(e.getFieldErrors().stream().map(RestValidationError::new).toList());

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RestValidationError> HttpMessageNotReadabledHandler(HttpMessageNotReadableException e) {// escre

        log.error("Erro de validação");
        String field = "";
        String message = "";

        if (e instanceof HttpMessageNotReadableException ||
                e.getCause() instanceof HttpMessageNotReadableException) {
            InvalidFormatException causa = (InvalidFormatException) e.getCause();
            field = causa.getPath().get(0).getFieldName();
            message = "";
            if (field.equals("data")) {
                message = "O formato deve ser yyyy-MM-dd";
            } else {
                message = "O formato deve ser HH:mm:ss";
            }
        }
        return ResponseEntity.badRequest().body(new RestValidationError(field, message));
    }

    @ExceptionHandler(RestNotFoundException.class)

    public ResponseEntity<ReturnAPI> RestNotFoundHandler(RestNotFoundException e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ReturnAPI(e.getMessage()));

    }

    @ExceptionHandler(ConstraintViolationException.class)

    public ResponseEntity<ReturnAPI> ConstraintViolationHandler(ConstraintViolationException e) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ReturnAPI(e.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)

    public ResponseEntity<ReturnAPI> DataIntegrityViolationHandler(DataIntegrityViolationException e) {

        if (e.getMessage().contains("23505-214"))

            return ResponseEntity.badRequest().body(new ReturnAPI("Usuario ou email já cadastrado!"));

        else

            return ResponseEntity.badRequest().body(new ReturnAPI(e.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)

    public ResponseEntity<ReturnAPI> BadCredentialsHandler(BadCredentialsException e) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ReturnAPI(e.getMessage()));
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ReturnAPI> JWTVerificationHandler(JWTVerificationException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ReturnAPI(e.getMessage()));
    }

}
