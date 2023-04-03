package br.com.fiap.aocs.models;

public class RestValidationError {
   
    private String field;

    private String message;

    public RestValidationError(String field,String message){
        this.field = field;
        this.message = message;
    }

    public RestValidationError(String message){
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

}
