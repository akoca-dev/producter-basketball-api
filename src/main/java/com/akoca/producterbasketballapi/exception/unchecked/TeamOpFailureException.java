package com.akoca.producterbasketballapi.exception.unchecked;

import java.util.Map;

public class TeamOpFailureException extends AbstractGraphQLException {

    public TeamOpFailureException(String message) {
        super(message);
    }

    public TeamOpFailureException(String message, Map<String, Object> additionParams) {
        super(message, additionParams);
    }
}