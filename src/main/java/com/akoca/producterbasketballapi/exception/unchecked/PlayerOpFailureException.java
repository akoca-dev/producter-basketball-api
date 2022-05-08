package com.akoca.producterbasketballapi.exception.unchecked;

import java.util.Map;

public class PlayerOpFailureException extends AbstractGraphQLException {

    public PlayerOpFailureException(String message) {
        super(message);
    }

    public PlayerOpFailureException(String message, Map<String, Object> additionParams) {
        super(message, additionParams);
    }
}