package br.com.fiap.aocs.exceptions;

public class RestNotFoundException extends RuntimeException {

    public RestNotFoundException(String message) {
        super(message);
    }

}