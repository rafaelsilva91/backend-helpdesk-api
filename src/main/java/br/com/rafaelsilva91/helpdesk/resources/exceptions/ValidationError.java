package br.com.rafaelsilva91.helpdesk.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError() {
        super();
    }

    public ValidationError(long timestamp, Integer status, String error, String message, String paths) {
        super(timestamp, status, error, message, paths);
        this.errors = errors;
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String FieldName, String message) {
        this.errors.add(new FieldMessage(FieldName, message));
    }

}