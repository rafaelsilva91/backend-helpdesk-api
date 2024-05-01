package br.com.rafaelsilva91.helpdesk.services.exceptions;

public class ObjectNotFountExceptions extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ObjectNotFountExceptions(String message) {
        super(message);
    }

    public ObjectNotFountExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
