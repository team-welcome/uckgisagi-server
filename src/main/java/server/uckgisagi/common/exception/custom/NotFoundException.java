package server.uckgisagi.common.exception.custom;

import server.uckgisagi.common.exception.ErrorResponseResult;

public class NotFoundException extends UckGiSaGiException {

    public NotFoundException(String message, ErrorResponseResult responseResult) {
        super(message, responseResult);
    }

}
