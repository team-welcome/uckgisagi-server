package server.uckgisagi.common.exception.custom;

import server.uckgisagi.common.exception.ErrorResponseResult;

public class ConflictException extends UckGiSaGiException {

    public ConflictException(String message, ErrorResponseResult responseResult) {
        super(message, responseResult);
    }

}
