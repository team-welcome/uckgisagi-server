package server.uckgisagi.common.advice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.uckgisagi.common.dto.ApiExceptionResponse;
import server.uckgisagi.common.exception.custom.UckGiSaGiException;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.ConstraintViolationException;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;
import static server.uckgisagi.common.exception.ErrorResponseResult.*;

@Slf4j
@RestControllerAdvice
@ApiIgnore
public class ExceptionControllerAdvice {

    /**
     * <b>UckGiSaGiException Custom Exception</b>
     */
    @ExceptionHandler(UckGiSaGiException.class)
    protected ResponseEntity<ApiExceptionResponse> handleBaseException(UckGiSaGiException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity
                .status(exception.getStatus())
                .body(ApiExceptionResponse.error(exception.getResponseResult()));
    }

    /**
     * <b>400 Bad Request</b><br>
     * Spring Validation
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    protected ApiExceptionResponse handleBadRequest(final BindException e) {
        log.error(e.getMessage());
        FieldError fieldError = Objects.requireNonNull(e.getFieldError());
        return ApiExceptionResponse.error(VALIDATION_EXCEPTION, String.format("%s (%s)", fieldError.getDefaultMessage(), fieldError.getField()));
    }

    /**
     * <b>400 Bad Request</b><br>
     * 잘못된 Enum 값이 입력된 경우 발생하는 Exception
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ApiExceptionResponse handleHttpMessageNotReadableException(final HttpMessageNotReadableException e) {
        log.error(e.getMessage());
        return ApiExceptionResponse.error(VALIDATION_ENUM_VALUE_EXCEPTION);
    }

    /**
     * <b>400 Bad Request</b><br>
     * RequestParam, RequestPath, RequestPart 등의 필드가 입력되지 않은 경우 발생하는 Exception
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MissingRequestValueException.class)
    protected ApiExceptionResponse handle(final MissingRequestValueException e) {
        log.error(e.getMessage());
        return ApiExceptionResponse.error(VALIDATION_REQUEST_MISSING_EXCEPTION);
    }

    /**
     * <b>400 Bad Request</b><br>
     * 잘못된 타입이 입력된 경우 발생하는 Exception
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(TypeMismatchException.class)
    protected ApiExceptionResponse handleTypeMismatchException(final TypeMismatchException e) {
        log.error(e.getMessage());
        return ApiExceptionResponse.error(VALIDATION_WRONG_TYPE_EXCEPTION, String.format("%s (%s)", VALIDATION_WRONG_TYPE_EXCEPTION.getMessage(), e.getValue()));
    }

    /**
     * <b>400 Bad Request</b>
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({
            InvalidFormatException.class,
            ServletRequestBindingException.class,
            ConstraintViolationException.class
    })
    protected ApiExceptionResponse handleInvalidFormatException(final Exception e) {
        log.error(e.getMessage());
        return ApiExceptionResponse.error(VALIDATION_EXCEPTION);
    }

    /**
     * <b>405 Method Not Allowed</b><br>
     * 지원하지 않은 HTTP method 호출 할 경우 발생하는 Exception
     */
    @ResponseStatus(METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ApiExceptionResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage());
        return ApiExceptionResponse.error(METHOD_NOT_ALLOWED_EXCEPTION);
    }

    /**
     * <b>406 Not Acceptable</b>
     */
    @ResponseStatus(NOT_ACCEPTABLE)
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    protected ApiExceptionResponse handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e) {
        log.error(e.getMessage());
        return ApiExceptionResponse.error(NOT_ACCEPTABLE_EXCEPTION);
    }

    /**
     * <b>415 UnSupported Media Type</b> <br>
     * 지원하지 않는 미디어 타입인 경우 발생하는 Exception
     */
    @ResponseStatus(UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeException.class)
    protected ApiExceptionResponse handleHttpMediaTypeException(final HttpMediaTypeException e) {
        log.error(e.getMessage(), e);
        return ApiExceptionResponse.error(UNSUPPORTED_MEDIA_TYPE_EXCEPTION);
    }

    /**
     * <b>500 Internal Server</b>
     */
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected ApiExceptionResponse handleException(final Exception exception) {
        log.error(exception.getMessage(), exception);
        return ApiExceptionResponse.error(INTERNAL_SERVER_EXCEPTION);
    }

}
