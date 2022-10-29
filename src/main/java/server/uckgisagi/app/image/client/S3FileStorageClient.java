package server.uckgisagi.app.image.client;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import server.uckgisagi.common.exception.custom.InternalServerException;

import java.io.IOException;
import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class S3FileStorageClient implements FileStorageClient {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    @Override
    public void uploadFile(MultipartFile file, String fileName) {
        try (InputStream inputStream = file.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, createObjectMetadata(file))
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new InternalServerException(String.format("파일 (%s) 입력 스트림을 가져오는 중 에러가 발생했습니다", file.getOriginalFilename()));
        }
    }

    private ObjectMetadata createObjectMetadata(MultipartFile file) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());
        return objectMetadata;
    }

    @Override
    public String getFileUrl(String fileName) {
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    @Override // TODO : 이렇게 삭제하는거 맞는지 확인
    public void deleteFile(String bucketImageUrl) {
        amazonS3.deleteObject(bucket, bucketImageUrl);
    }

}
