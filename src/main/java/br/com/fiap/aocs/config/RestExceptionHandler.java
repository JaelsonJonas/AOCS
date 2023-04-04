package br.com.fiap.aocs.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import br.com.fiap.aocs.exceptions.RestNotFoundException;
import br.com.fiap.aocs.models.RestValidationError;
import br.com.fiap.aocs.models.ReturnAPI;

@RestControllerAdvice
public class RestExceptionHandler {

    Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(MethodArgumentNotValidException.class) // incluir o nome da exception.class
    public ResponseEntity<List<RestValidationError>> MethodArgumentNotValidHandler(MethodArgumentNotValidException e) {// escre

        log.error("Erro de validação");
        List<RestValidationError> fieldErrors = new ArrayList<>();

        e.getFieldErrors().forEach(r -> fieldErrors.add(new RestValidationError(r.getField(), r.getDefaultMessage())));

        return ResponseEntity.badRequest().body(fieldErrors);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class) // incluir o nome da exception.class
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

    @ExceptionHandler(RestNotFoundException.class) // incluir o nome da

    public ResponseEntity<ReturnAPI> RestNotFoundHandler(RestNotFoundException e) {// escre

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ReturnAPI(e.getMessage()));

    }
}
