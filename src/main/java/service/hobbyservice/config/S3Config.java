package service.hobbyservice.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;


    @Bean
    public AmazonS3Client amazonS3Client() {
        // AWS 접근을 위한 기본 자격 증명 생성
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

        // AmazonS3Client 객체 생성 및 반환
        return (AmazonS3Client) AmazonS3ClientBuilder
                .standard()  // 표준 빌더 사용
                .withRegion(region)  // 지정된 AWS 리전 설정
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))  // 자격 증명 제공자 설정
                .build();  // 클라이언트 빌드
    }
}