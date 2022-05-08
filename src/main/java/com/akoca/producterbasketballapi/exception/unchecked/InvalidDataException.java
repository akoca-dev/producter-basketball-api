package com.akoca.producterbasketballapi.exception.unchecked;

import java.util.Map;

public class InvalidDataException extends AbstractGraphQLException {

    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(String message, Map<String, Object> additionParams) {
        super(message, additionParams);
    }
}