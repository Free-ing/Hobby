package service.hobbyservice.service;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import service.hobbyservice.config.S3Config;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

// 이미지 업로드 서비스를 정의하는 클래스
@Service
public class ImageService {

    // S3 설정을 위한 객체
    private S3Config s3Config;

    // 생성자 주입을 통해 S3Config 객체를 주입받음
    @Autowired
    public ImageService(S3Config s3Config) {
        this.s3Config = s3Config;
    }

    // application.yml 또는 properties 파일에서 S3 버킷 이름을 주입받음
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // 로컬 환경에서 임시로 파일을 저장할 경로
    private String localLocation = "로컬환경경로";

    // 이미지 업로드를 처리하는 메서드
    public String imageUpload(MultipartRequest request) throws IOException {
        // 클라이언트로부터 전송된 파일을 가져옴
        MultipartFile file = request.getFile("upload");

        // 원본 파일 이름 추출
        String fileName = file.getOriginalFilename();

        // 파일 확장자 추출
        String ext = fileName.substring(fileName.indexOf("."));

        // UUID를 이용해 유니크한 파일 이름 생성
        String uuidFileName = UUID.randomUUID() + ext;

        // 로컬에 임시 저장할 파일의 전체 경로
        String localPath = localLocation + uuidFileName;

        // 로컬 파일 객체 생성
        File localFile = new File(localPath);

        // MultipartFile을 로컬 파일로 전송
        file.transferTo(localFile);

        // S3에 파일 업로드. PublicRead 권한으로 설정하여 모든 사용자가 읽을 수 있게 함
        s3Config.amazonS3Client().putObject(new PutObjectRequest(bucket, uuidFileName, localFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        // 업로드된 파일의 S3 URL 가져오기
        String s3Url = s3Config.amazonS3Client().getUrl(bucket, uuidFileName).toString();

        // 임시로 저장한 로컬 파일 삭제
        localFile.delete();

        // S3 URL 반환
        return s3Url;
    }
}