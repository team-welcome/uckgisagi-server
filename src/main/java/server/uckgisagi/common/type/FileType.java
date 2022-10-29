package server.uckgisagi.common.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import server.uckgisagi.common.exception.custom.ValidationException;
import server.uckgisagi.common.util.FileUtils;
import server.uckgisagi.common.util.UuidUtils;

import static server.uckgisagi.common.exception.ErrorResponseResult.*;

@Getter
@RequiredArgsConstructor
public enum FileType {

    STORE_IMAGE("리필 스테이션 매장 이미지", "uckgisagi-image/store/", FileContentType.IMAGE),
    POST_IMAGE("(유저) 인증 이미지", "uckgisagi-image/certification/", FileContentType.IMAGE),
    ;

    private final String description;
    private final String directory;
    private final FileContentType contentType;

    public void validateAvailableContentType(String contentType) {
        this.contentType.validateAvailableContentType(contentType);
    }

    /**
     * 파일의 기존의 확장자를 유지한 채, 유니크한 파일의 이름을 반환합니다.
     */
    public String createUniqueFileNameWithExtension(String originFileName) {
        if (originFileName == null) {
            throw new ValidationException("잘못된 파일의 originFilename 입니다", FORBIDDEN_FILE_TYPE_EXCEPTION);
        }
        String extension = FileUtils.getFileExtension(originFileName);
        return getFileNameWithDirectory(UuidUtils.generate().concat(extension));
    }

    private String getFileNameWithDirectory(String fileName) {
        return this.directory.concat(fileName);
    }

}
