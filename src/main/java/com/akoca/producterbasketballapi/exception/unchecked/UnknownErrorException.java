package com.akoca.producterbasketballapi.exception.unchecked;

import java.util.Map;

public class UnknownErrorException extends AbstractGraphQLException {

    public UnknownErrorException(String message) {
        super(message);
    }

    public UnknownErrorException(String message, Map<String, Object> additionParams) {
        super(message, additionParams);
    }
}