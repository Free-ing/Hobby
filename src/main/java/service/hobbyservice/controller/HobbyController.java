package service.hobbyservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import service.hobbyservice.base.BaseResponse;
import service.hobbyservice.base.exception.code.RestApiException;
import service.hobbyservice.base.exception.code.RoutineErrorStatus;
import service.hobbyservice.dto.request.HobbyRequestDto;
import service.hobbyservice.dto.response.HobbyResponseDto;
import service.hobbyservice.service.HobbyCommonService;
import service.hobbyservice.service.HobbyQueryService;
import service.hobbyservice.service.ImageService;
import service.hobbyservice.service.TokenProviderService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hobby-service")
public class HobbyController {

    private final HobbyCommonService hobbyCommonService;
    private final HobbyQueryService hobbyQueryService;
    private final TokenProviderService tokenProviderService;
    private final ImageService imageService;



    @GetMapping("/health_check")
    public String status(){
        return "hobby Service is working fine!";
    }



    @PostMapping(value = "/record/{userId}")
    public BaseResponse recordHobby(
            @RequestPart("hobbyRecord") @Valid HobbyRequestDto.hobbyRecordDto hobbyRecordDto,
            @RequestPart("file") MultipartFile file,
//            @RequestParam(name="file", required = false) MultipartFile file,
            @PathVariable Long userId
    ) {
        System.out.println(file.getOriginalFilename());
        try {
            // 이미지 업로드 및 URL 받기
            String imageUrl = imageService.imageUpload(file);

            // hobbyRecordDto에 이미지 URL 설정
            hobbyRecordDto.setPhotoUrl(imageUrl);

            // 취미 기록 생성
            Long recordId = hobbyCommonService.createHobbyRecord(hobbyRecordDto, userId);

            return BaseResponse.onSuccess(recordId);
        } catch (IOException e) {
            return BaseResponse.onFail("실패하였습니다.");
        }
    }

    //Todo: 루틴 추가하기
    @PostMapping("/routine/{userId}")
    public BaseResponse<Long> addHobbyRoutine(
            @RequestBody @Valid HobbyRequestDto.hobbyRoutineDto hobbyRoutineDto,
            @PathVariable Long userId
//            @RequestHeader("Authorization") String authorizationHeader
    ){
//        Long userId = tokenProviderService.getUserIdFromToken(authorizationHeader);

        Long routineId = hobbyCommonService.addHobbyRoutine(hobbyRoutineDto, userId);

        return BaseResponse.onSuccess(routineId);
    }


    //Todo: 회원의 취미 루틴 조회
    @GetMapping("/routine-list/{userId}")
    public BaseResponse<List<HobbyResponseDto.HobbyRoutineDto>> getHobbyRoutinesByUserId(
            @PathVariable Long userId
            // @RequestHeader("Authorization") String authorizationHeader

    ) {

//        Long userId = tokenProviderService.getUserIdFromToken(authorizationHeader);
        List<HobbyResponseDto.HobbyRoutineDto> hobbyRoutines = hobbyQueryService.getHobbyRoutineListByUserId(userId);
        return BaseResponse.onSuccess(hobbyRoutines);
    }

    //Todo: 취미 기록 수정
    @PutMapping("/record/{recordId}/{userId}")
    public BaseResponse<HobbyResponseDto.HobbyRecordDto> updateRoutineRecord(
            @PathVariable Long recordId,
            @PathVariable Long userId,
            @RequestBody @Valid HobbyRequestDto.hobbyRecordDto hobbyRecordDto
            // @RequestHeader("Authorization") String authorizationHeader
    ){
        return BaseResponse.onSuccess(hobbyCommonService.updateHobbyRecord(hobbyRecordDto,recordId,userId));
    }


    //Todo: 취미 기록 조회
    @GetMapping("/album-list/{userId}")
    public BaseResponse<List<HobbyResponseDto.AlbumResponseDto>> getAlbumList(
            @PathVariable Long userId,
            @RequestParam Integer year,
            @RequestParam Integer month
            //@RequestHeader("Authorization") String authorizationHeader

    ) {
//        Long userId = tokenProviderService.getUserIdFromToken(authorizationHeader);

        // 로직 구현
        List<HobbyResponseDto.AlbumResponseDto> albums = hobbyQueryService.getAlbumList(year, month,userId);
        return BaseResponse.onSuccess(albums);
    }


    //Todo: 취미 루틴 삭제
    @DeleteMapping("/routine/{routineId}/{userId}")
    public BaseResponse<String> deleteHobbyRoutine(
            @PathVariable Long routineId,
            @PathVariable Long userId
            //@RequestHeader("Authorization") String authorizationHeader

    ){
        //        Long userId = tokenProviderService.getUserIdFromToken(authorizationHeader);

      hobbyCommonService.deleteHobbyRoutine(routineId,userId);
      return BaseResponse.onSuccess("성공적으로 취미 루틴을 삭제했습니다.");
    }

    //Todo: 취미 기록 삭제
    @DeleteMapping("/record/{recordId}/{userId}")
    public BaseResponse<String> deleteHobbyRecord(
            @PathVariable Long recordId,
            @PathVariable Long userId
            //@RequestHeader("Authorization") String authorizationHeader

    ){
        //        Long userId = tokenProviderService.getUserIdFromToken(authorizationHeader);

        hobbyCommonService.deleteHobbyRecord(recordId,userId);
        return BaseResponse.onSuccess("성공적으로 취미 기록을 삭제했습니다.");
    }

    //Todo: 취미 프로그램 데이터 모두 삭제
    @DeleteMapping("/{userId}")
    public BaseResponse<String> deleteHobbyData(
            @PathVariable Long recordId,
            @PathVariable Long userId
            //@RequestHeader("Authorization") String authorizationHeader

    ){
        //        Long userId = tokenProviderService.getUserIdFromToken(authorizationHeader);

        hobbyCommonService.deleteHobbyRecord(recordId,userId);
        return BaseResponse.onSuccess("성공적으로 취미 기록을 삭제했습니다.");
    }



}


