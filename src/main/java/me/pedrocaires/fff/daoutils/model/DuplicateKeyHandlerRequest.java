package me.pedrocaires.fff.daoutils.model;

import me.pedrocaires.fff.exception.CustomException;

public class DuplicateKeyHandlerRequest {

    private final String duplicateKey;

    private final CustomException exception;

    public DuplicateKeyHandlerRequest(String duplicateKey, CustomException exception) {
        this.duplicateKey = duplicateKey;
        this.exception = exception;
    }

    public String getDuplicateKey(){
        return duplicateKey;
    }

    public CustomException getException(){
       return exception;
    }    
}
