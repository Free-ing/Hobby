package service.hobbyservice.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
@Slf4j
public class ImageUploadService {

    private final AmazonS3 amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;
    private AmazonS3 amazonS3;

    @Autowired
    public ImageUploadService(AmazonS3 amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    public String uploadImage(MultipartFile file) {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());

            amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata));

            return amazonS3Client.getUrl(bucketName, fileName).toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image", e);
        }
    }

    public void deleteImage(String imageUrl) {
        if (imageUrl == null || imageUrl.isEmpty()) {
            return;
        }

        try {
            // URL에서 파일 키(경로) 추출
            String fileKey = extractFileKeyFromUrl(imageUrl);

            // S3에서 파일 삭제
            DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName, fileKey);
            amazonS3.deleteObject(deleteObjectRequest);

            log.info("Successfully deleted file from S3: {}", fileKey);
        } catch (AmazonServiceException e) {
            log.error("Error occurred while deleting file from S3. ImageUrl: {}", imageUrl, e);
            throw new RuntimeException("Failed to delete image from S3", e);
        } catch (Exception e) {
            log.error("Unexpected error while deleting file from S3. ImageUrl: {}", imageUrl, e);
            throw new RuntimeException("Unexpected error while deleting image", e);
        }
    }

    private String extractFileKeyFromUrl(String imageUrl) {
        try {
            // URL 디코딩
            String decodedUrl = URLDecoder.decode(imageUrl, StandardCharsets.UTF_8.toString());

            // URL에서 파일 키 추출
            // 예: https://bucket-name.s3.region.amazonaws.com/folder/image.jpg
            // -> folder/image.jpg
            URL url = new URL(decodedUrl);
            String path = url.getPath();

            // 첫 번째 '/'를 제거하여 실제 S3 키 얻기
            return path.startsWith("/") ? path.substring(1) : path;
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract file key from URL: " + imageUrl, e);
        }
    }
}