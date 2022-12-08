package server.uckgisagi.common.exception.custom;

import server.uckgisagi.common.exception.ErrorResponseResult;

public class BusinessException extends UckGiSaGiException{
    public BusinessException(String message, ErrorResponseResult errorResponseResult) {
        super(message, errorResponseResult);
    }
}
