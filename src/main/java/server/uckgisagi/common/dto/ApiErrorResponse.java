package server.uckgisagi.common.dto;

import lombok.*;
import server.uckgisagi.common.exception.ErrorResponseResult;
import server.uckgisagi.common.exception.ErrorStatusCode;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiErrorResponse {

    private ErrorStatusCode statusCode;
    private int status;
    private String message;

    public static ApiErrorResponse error(ErrorResponseResult responseResult) {
        return new ApiErrorResponse(responseResult.getStatusCode(), responseResult.getStatus(), responseResult.getMessage());
    }

    public static ApiErrorResponse error(ErrorResponseResult responseResult, String message) {
        return new ApiErrorResponse(responseResult.getStatusCode(), responseResult.getStatus(), message);
    }

}
