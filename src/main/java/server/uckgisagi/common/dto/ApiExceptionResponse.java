package server.uckgisagi.common.dto;

import lombok.*;
import server.uckgisagi.common.exception.ErrorResponseResult;
import server.uckgisagi.common.exception.ErrorStatusCode;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiExceptionResponse {

    private ErrorStatusCode statusCode;
    private int status;
    private String message;

    public static ApiExceptionResponse error(ErrorResponseResult responseResult) {
        return new ApiExceptionResponse(responseResult.getStatusCode(), responseResult.getStatus(), responseResult.getMessage());
    }

    public static ApiExceptionResponse error(ErrorResponseResult responseResult, String message) {
        return new ApiExceptionResponse(responseResult.getStatusCode(), responseResult.getStatus(), message);
    }

}
