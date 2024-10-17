package service.hobbyservice.controller;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.hobbyservice.base.BaseResponse;
import service.hobbyservice.dto.request.HobbyRequestDto;
import service.hobbyservice.dto.response.HobbyResponseDto;
import service.hobbyservice.service.HobbyCommonService;
import service.hobbyservice.service.HobbyQueryService;
import service.hobbyservice.service.TokenProviderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hobby-service")
public class HobbyController {

    private final HobbyCommonService hobbyCommonService;
    private final HobbyQueryService hobbyQueryService;
    private final TokenProviderService tokenProviderService;


    @GetMapping("/health_check")
    public String status(){
        return "hobby Service is working fine!";
    }



    //Todo: 취미 기록하기
    @PostMapping("/record/{userId}")
    public BaseResponse<Long> recordHobby(
            @RequestBody @Valid HobbyRequestDto.hobbyRecordDto hobbyRecordDto,
            @PathVariable Long userId
//            @RequestHeader("Authorization") String authorizationHeader
    ){
//        Long userId = tokenProviderService.getUserIdFromToken(authorizationHeader);
        System.out.println(hobbyRecordDto.getHobbyName());
        Long recordId = hobbyCommonService.createHobbyRecord(hobbyRecordDto, userId);

        return BaseResponse.onSuccess(recordId);
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
    @GetMapping("/album-list")
    public ResponseEntity<List<HobbyResponseDto.AlbumResponseDto>> getAlbumList(
            @RequestParam Integer year,
            @RequestParam Integer month
    ) {
//        Long userId = tokenProviderService.getUserIdFromToken(authorizationHeader);

        // 로직 구현
        List<HobbyResponseDto.AlbumResponseDto> albums = hobbyCommonService.getAlbumList(year, month);
        return ResponseEntity.ok(albums);
    }





}


