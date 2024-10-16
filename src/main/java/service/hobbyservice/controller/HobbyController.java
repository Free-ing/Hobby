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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hobby-service")
public class HobbyController {

    private final HobbyCommonService hobbyCommonService;
    private final HobbyQueryService hobbyQueryService;

    //Todo: 취미 기록하기
    @PostMapping("/record/{userId}")
    public BaseResponse<Long> recordHobby(
            @RequestBody @Valid HobbyRequestDto.hobbyRecordDto hobbyRecordDto,
            @PathVariable Long userId
//            @RequestHeader("Authorization") String authorizationHeader
    ){
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
        Long routineId = hobbyCommonService.addHobbyRoutine(hobbyRoutineDto, userId);

        return BaseResponse.onSuccess(routineId);
    }


    //Todo: 회원의 취미 루틴 조회
    @GetMapping("/routine-list/{userId}")
    public BaseResponse<List<HobbyResponseDto.HobbyRoutineDto>> getHobbyRoutinesByUserId(
            @PathVariable Long userId
            // @RequestHeader("Authorization") String authorizationHeader

    ) {
        List<HobbyResponseDto.HobbyRoutineDto> hobbyRoutines = hobbyQueryService.getHobbyRoutineListByUserId(userId);
        return BaseResponse.onSuccess(hobbyRoutines);
    }
}


