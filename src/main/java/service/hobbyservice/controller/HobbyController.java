package service.hobbyservice.controller;

import jakarta.validation.Valid;
import jakarta.ws.rs.PATCH;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.hobbyservice.base.BaseResponse;
import service.hobbyservice.dto.request.HobbyRequestDto;
import service.hobbyservice.dto.request.ImageUploadResponseDto;
import service.hobbyservice.dto.request.SurveyResultDto;
import service.hobbyservice.dto.response.HobbyResponseDto;
import service.hobbyservice.dto.response.RoutineTrackerDto;
import service.hobbyservice.service.*;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hobby-service")
public class HobbyController {

    private final HobbyCommonService hobbyCommonService;
    private final HobbyQueryService hobbyQueryService;
    private final TokenProviderService tokenProviderService;
    private final ImageUploadService imageUploadService;
    private final ImageService imageService;
    private final OpenAiService openAiService;


    @GetMapping("/health_check")
    public String status(){
        return "hobby Service is working fine!";
    }


    //Todo: 취미 기록하기
    @PostMapping(value = "/record", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BaseResponse<Long> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam LocalDate date,
            @RequestPart @Valid HobbyRequestDto.hobbyRecordDto hobbyRecordDto,
//            @PathVariable Long userId
            @RequestHeader("Authorization") String authorizationHeader


    ) throws IOException {
        Long userId = tokenProviderService.getUserIdFromToken(authorizationHeader);

        String imageUrl = imageUploadService.uploadImage(file);

            // hobbyRecordDto에 이미지 URL 설정
            hobbyRecordDto.setPhotoUrl(imageUrl);

            // 취미 기록 생성
            Long recordId = hobbyCommonService.createHobbyRecord(hobbyRecordDto, userId, date);

            return BaseResponse.onSuccess(recordId);

    }


    //Todo: 루틴 추가하기
    @PostMapping("/routine")
    public BaseResponse<Long> addHobbyRoutine(
            @RequestBody@Valid HobbyRequestDto.hobbyRoutineDto hobbyRoutineDto,
//            @PathVariable Long userId
            @RequestHeader("Authorization") String authorizationHeader
    ){
        Long userId = tokenProviderService.getUserIdFromToken(authorizationHeader);

//        String imageUrl = imageUploadService.uploadImage(file);

        // hobbyRecordDto에 이미지 URL 설정
        hobbyRoutineDto.setImageUrl(hobbyRoutineDto.getImageUrl());


        //routine 생성
        Long routineId = hobbyCommonService.addHobbyRoutine(hobbyRoutineDto, userId);

        return BaseResponse.onSuccess(routineId);
    }


    //Todo: 회원의 취미 루틴 조회
    @GetMapping("/routine-list")
    public BaseResponse<List<HobbyResponseDto.HobbyRoutineDto>> getHobbyRoutinesByUserId(
//            @PathVariable Long userId
             @RequestHeader("Authorization") String authorizationHeader

    ) {
        Long userId = tokenProviderService.getUserIdFromToken(authorizationHeader);

        List<HobbyResponseDto.HobbyRoutineDto> hobbyRoutines = hobbyQueryService.getHobbyRoutineListByUserId(userId);
        System.out.println(hobbyRoutines);
        return BaseResponse.onSuccess(hobbyRoutines);
    }

    //Todo: 취미 기록 수정
    @PutMapping(value =  "/record/{recordId}" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BaseResponse<HobbyResponseDto.HobbyRecordDto> updateRoutineRecord(
            @RequestPart @Valid HobbyRequestDto.hobbyRecordDto hobbyRecordDto,
            @RequestParam MultipartFile file,
            @PathVariable Long recordId,
//            @PathVariable Long userId
            @RequestHeader("Authorization") String authorizationHeader
    ){
        Long userId = tokenProviderService.getUserIdFromToken(authorizationHeader);

        String imageUrl;

        if (file.isEmpty()){
            imageUrl = null;
        }else{
            imageUrl = imageUploadService.uploadImage(file);
        }
        return BaseResponse.onSuccess(hobbyCommonService.updateHobbyRecord(hobbyRecordDto,imageUrl,recordId,userId));
    }


    //Todo: 취미 기록 조회
    @GetMapping("/album-list")
    public BaseResponse<List<HobbyResponseDto.AlbumResponseDto>> getAlbumList(
//            @PathVariable Long userId,
            @RequestParam Integer year,
            @RequestParam Integer month,
            @RequestHeader("Authorization") String authorizationHeader

    ) {
        Long userId = tokenProviderService.getUserIdFromToken(authorizationHeader);

        // 로직 구현
        List<HobbyResponseDto.AlbumResponseDto> albums = hobbyQueryService.getAlbumList(year, month,userId);
        return BaseResponse.onSuccess(albums);
    }


    //Todo: 취미 루틴 삭제
    @DeleteMapping("/routine/{routineId}")
    public BaseResponse<String> deleteHobbyRoutine(
            @PathVariable Long routineId,
//            @PathVariable Long userId
            @RequestHeader("Authorization") String authorizationHeader

    ){
        Long userId = tokenProviderService.getUserIdFromToken(authorizationHeader);

        hobbyCommonService.deleteHobbyRoutine(routineId,userId);
        return BaseResponse.onSuccess("성공적으로 취미 루틴을 삭제했습니다.");
    }

    //Todo: 취미 기록 삭제
    @DeleteMapping("/record/{recordId}")
    public BaseResponse<String> deleteHobbyRecord(
            @PathVariable Long recordId,
//            @PathVariable Long userId
            @RequestHeader("Authorization") String authorizationHeader

    ){
        Long userId = tokenProviderService.getUserIdFromToken(authorizationHeader);

        hobbyCommonService.deleteHobbyRecord(recordId,userId);
        return BaseResponse.onSuccess("성공적으로 취미 기록을 삭제했습니다.");
    }


    //Todo: 회원의 모든 취미 기록 삭제
    @DeleteMapping("/users/{userId}")
    public BaseResponse<String> deleteHobbyRecord(
        @PathVariable Long userId
    ){
        hobbyCommonService.deleteHobbyData(userId);
        return BaseResponse.onSuccess("성공적으로 취미 기록을 삭제했습니다.");
    }



    //Todo: 취미 루틴 수정(사진, 취미이름)
    @PatchMapping(value = "/routine/{routineId}")
    public BaseResponse updateHobbyRoutine(
            @RequestBody @Valid HobbyRequestDto.hobbyRoutineDto hobbyRoutineDto,
            @PathVariable Long routineId,
//            @PathVariable Long userId
            @RequestHeader("Authorization") String authorizationHeader

    ){
        Long userId = tokenProviderService.getUserIdFromToken(authorizationHeader);

        // hobbyRecordDto에 이미지 URL 설정
        hobbyCommonService.updateHobbyRoutine(hobbyRoutineDto,routineId, userId);

        return BaseResponse.onSuccess("성공적으로 취미를 수정하였습니다.");
    }

    //TODO: AI 취미 추천 기능
    @PostMapping("/ai/recommend")
    public BaseResponse generateResponse(
            @RequestBody SurveyResultDto.surveyResultDto surveyResultDto,
            @RequestHeader("Authorization") String authorizationHeader

    ) {
        Long userId = tokenProviderService.getUserIdFromToken(authorizationHeader);
        List<HobbyResponseDto.AiHobbyResponseDto> recommendations = openAiService.generateHobbyRecommendations(surveyResultDto);
        return BaseResponse.onSuccess(recommendations);
    }

    //Todo: 월별 취미 루틴 트래커 조회
    @GetMapping("/tracker")
    public BaseResponse<List<RoutineTrackerDto.HobbyRoutineTrackerDto>> getRoutineTracker(
            @RequestParam int year,
            @RequestParam int month,
//            @PathVariable Long userId
            @RequestHeader("Authorization") String authorizationHeader

    ){
        Long userId = tokenProviderService.getUserIdFromToken(authorizationHeader);

        return BaseResponse.onSuccess(hobbyQueryService.getHobbyRoutineTrackers(userId,year,month));
    }

    //Todo : 취미 기본 기능 생성
    @PostMapping("/default-routine/{userId}")
    public void createDefaultRoutine(
            @PathVariable Long userId
    ){
        hobbyCommonService.createDefaultService(userId);
    }

    //Todo: 하나라도 수행한 일정이 있다면 조회하는 그 날짜 반환하기

    @GetMapping("/home/record-week/{userId}")
    public BaseResponse<?> getDate(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @PathVariable Long userId
    ) {
        List<HobbyResponseDto.DayCompleteRoutine> existingDates =
                hobbyQueryService.getCompleteDate(startDate, endDate, userId);

        if (existingDates.isEmpty()) {
            return BaseResponse.onSuccess(Collections.emptyList());
        }

        return BaseResponse.onSuccess(existingDates);
    }
}