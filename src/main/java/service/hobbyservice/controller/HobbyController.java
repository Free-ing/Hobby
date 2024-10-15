package service.hobbyservice.controller;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import service.hobbyservice.base.BaseResponse;
import service.hobbyservice.dto.request.HobbyRequestDto;
import service.hobbyservice.service.HobbyCommonService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hobby-service")
public class HobbyController {

    private final HobbyCommonService hobbyCommonService;
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
}
