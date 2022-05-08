package com.akoca.producterbasketballapi.exception.unchecked;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractGraphQLException extends RuntimeException implements GraphQLError {
    private final Map<String, Object> parameters;

    protected AbstractGraphQLException(String message) {
        super(message);
        parameters = new HashMap<>();
    }

    protected AbstractGraphQLException(String message, Map<String, Object> additionParams) {
        super(message);
        parameters = additionParams;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return Collections.emptyList();
    }

    @Override
    public ErrorType getErrorType() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return this.parameters;
    }
}