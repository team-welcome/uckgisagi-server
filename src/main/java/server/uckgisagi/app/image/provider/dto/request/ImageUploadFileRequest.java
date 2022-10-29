package server.uckgisagi.app.image.provider.dto.request;

import lombok.*;
import server.uckgisagi.common.type.FileType;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageUploadFileRequest implements UploadFileRequest {

    private FileType type;

    public static ImageUploadFileRequest from(FileType type) {
        return new ImageUploadFileRequest(type);
    }

//    public String getFileNameWithBucketDirectory(String originalFilename) {
//        return type.createUniqueFileNameWithExtension(originalFilename);
//    }

    private static final String SEPARATOR = "/";
    private static final String IMAGE_CONTENT_TYPE_TYPE = "image";

}
