package server.uckgisagi.common.dto;

import lombok.*;
import server.uckgisagi.common.success.SuccessResponseResult;
import server.uckgisagi.common.success.SuccessStatusCode;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiSuccessResponse<T> {

    private static final ApiSuccessResponse<String> SUCCESS = success("");

    private SuccessStatusCode statusCode;
    private String message;
    private T data;

    public static <T> ApiSuccessResponse<T> success(T data) {
        return new ApiSuccessResponse<>(SuccessStatusCode.OK, "", null);
    }

    public static <T> ApiSuccessResponse<T> success(SuccessResponseResult responseResult, T data) {
        return new ApiSuccessResponse<>(responseResult.getStatusCode(), responseResult.getMessage(), data);
    }

}
