package service.hobbyservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;
import service.hobbyservice.service.ImageService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 이미지 업로드를 처리하는 컨트롤러 클래스
@Controller
public class ImageController {

    // 이미지 서비스 객체
    private ImageService imageService;

    // 생성자 주입을 통해 ImageService 객체를 주입받음
    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    // POST 요청으로 /image/upload 엔드포인트를 처리하는 메서드
    @PostMapping("/image/upload")
    // @ResponseBody 어노테이션은 반환값을 HTTP 응답 본문에 직접 쓰도록 지시
    @ResponseBody
    public Map<String, Object> imageUpload(MultipartRequest request) throws Exception {
        // 응답 데이터를 저장할 Map 객체 생성
        Map<String, Object> responseData = new HashMap<>();

        try {
            // ImageService를 이용해 이미지를 업로드하고 S3 URL을 받아옴
            String s3Url = imageService.imageUpload(request);

            // 업로드 성공 시 응답 데이터 설정
            responseData.put("uploaded", true);
            responseData.put("url", s3Url);

            return responseData;

        } catch (IOException e) {
            // 업로드 실패 시 응답 데이터 설정
            responseData.put("uploaded", false);

            return responseData;
        }
    }
}