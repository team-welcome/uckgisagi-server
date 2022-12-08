package server.uckgisagi.app.image.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import server.uckgisagi.app.image.client.S3FileStorageClient;
import server.uckgisagi.app.image.provider.dto.request.UploadFileRequest;

@Component
@RequiredArgsConstructor
public class UploadProvider {

    private final S3FileStorageClient fileStorageClient;

    public String uploadFile(UploadFileRequest request, MultipartFile file) {
        request.validateAvailableContentType(file.getContentType());
        String fileName = request.getFileNameWithBucketDirectory(file.getOriginalFilename());
        fileStorageClient.uploadFile(file, fileName);
        return fileStorageClient.getFileUrl(fileName);
    }

}
