package me.pedrocaires.fff.endpoint.environment.model;

import me.pedrocaires.fff.exception.AlreadyExistException;

public class MainEnvironmentAlreadyExistsException extends AlreadyExistException {

    public MainEnvironmentAlreadyExistsException(){
        super("%s environment is already defined", "Main");
    }
    
}
