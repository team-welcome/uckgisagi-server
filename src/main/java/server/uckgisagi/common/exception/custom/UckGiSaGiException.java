package server.uckgisagi.common.exception.custom;

import lombok.Getter;
import server.uckgisagi.common.exception.ErrorResponseResult;

@Getter
public abstract class UckGiSaGiException extends RuntimeException {

    private final ErrorResponseResult responseResult;

    public UckGiSaGiException(String message, ErrorResponseResult errorResponseResult) {
        super(message);
        this.responseResult = errorResponseResult;
    }

    public int getStatus() {
        return responseResult.getStatus();
    }

}
