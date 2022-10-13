package server.uckgisagi.common.exception.custom;

import server.uckgisagi.common.exception.ErrorResponseResult;

public class UnAuthorizedException extends UckGiSaGiException {

    public UnAuthorizedException(String message) {
        super(message, ErrorResponseResult.UNAUTHORIZED_EXCEPTION);
    }

}
