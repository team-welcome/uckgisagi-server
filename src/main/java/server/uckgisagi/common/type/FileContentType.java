package server.uckgisagi.common.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import server.uckgisagi.common.exception.custom.ValidationException;

import static server.uckgisagi.common.exception.ErrorResponseResult.*;

@Getter
@RequiredArgsConstructor
public enum FileContentType {

    IMAGE("image"),
    ;

    private final String prefix;

    public void validateAvailableContentType(String contentType) {
        if (contentType != null && contentType.contains(SEPARATOR) && prefix.equals(getContentTypePrefix(contentType))) {
            return;
        }
        throw new ValidationException(String.format("허용되지 않은 파일 형식 (%s) 입니다", contentType), FORBIDDEN_FILE_TYPE_EXCEPTION);
    }

    private static String getContentTypePrefix(String contentType) {
        return contentType.split(SEPARATOR)[0];
    }

    private static final String SEPARATOR = "/";

}
