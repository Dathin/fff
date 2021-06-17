package me.pedrocaires.fff.exception;

public class FormValidationResponse {

    private final String fieldName;

    private final String message;

    public FormValidationResponse(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}
