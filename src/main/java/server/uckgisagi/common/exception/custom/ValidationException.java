package server.uckgisagi.common.exception.custom;

import server.uckgisagi.common.exception.ErrorResponseResult;

public class ValidationException extends UckGiSaGiException {

    public ValidationException(String message, ErrorResponseResult responseResult) {
        super(message, responseResult);
    }

    public ValidationException(String message) {
        super(message, ErrorResponseResult.VALIDATION_EXCEPTION);
    }

}
