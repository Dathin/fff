package me.pedrocaires.fff.daoutils;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import me.pedrocaires.fff.daoutils.model.DuplicateKeyHandlerRequest;

@Component
public class DuplicateKeyHandler {
    
    public void handleDuplicateKey(List<DuplicateKeyHandlerRequest> duplicateKeyExceptions, DuplicateKeyException ex){
        duplicateKeyExceptions.forEach(duplicateKeyException -> {
            if(ex.getMessage().contains(duplicateKeyException.getDuplicateKey())){
                throw duplicateKeyException.getException();
            }
        });
        throw ex;
    }

}
