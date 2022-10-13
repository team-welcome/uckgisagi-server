package server.uckgisagi.common.exception.custom;

import server.uckgisagi.common.exception.ErrorResponseResult;

public class InternalServerException extends UckGiSaGiException {

    public InternalServerException(String message) {
        super(message, ErrorResponseResult.INTERNAL_SERVER_EXCEPTION);
    }

}
