package br.com.fiap.aocs.models;

import org.springframework.validation.FieldError;

public class RestValidationError {

    private String field;

    private String message;

    public RestValidationError(FieldError e) {
        this.field = e.getField();
        this.message = e.getDefaultMessage();

    }

    

    public RestValidationError(String field, String message) {
        this.field = field;
        this.message = message;
    }



    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

}
