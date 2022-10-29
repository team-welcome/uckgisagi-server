package server.uckgisagi.app.image.client;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageClient {

    /**
     * AmazonS3 Bucket 에 파일을 저장한다
     * @param file 유저가 등록하려 하는 파일
     * @param fileName 파일의 기존의 확장자를 유지한 채 반환된 유니크한 파일의 이름
     */
    void uploadFile(MultipartFile file, String fileName);

    /**
     * AmazonS3 Bucket 에 저장된 파일 경로 url 을 가져온다
     * @param fileName 파일의 기존의 확장자를 유지한 채 반환된 유니크한 파일의 이름
     * @return AmazonS3 Bucket 에 저장된 파일 경로 url
     */
    String getFileUrl(String fileName);

    /**
     *
     * @param bucketImageUrl
     */
    void deleteFile(String bucketImageUrl);

}
