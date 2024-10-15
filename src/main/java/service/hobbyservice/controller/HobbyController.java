package service.hobbyservice.controller;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import service.hobbyservice.base.BaseResponse;
import service.hobbyservice.dto.request.HobbyRequestDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hobby-service")
public class HobbyController {

    @PostMapping("/record/{userId}")
    public BaseResponse<> recordHobby(
            @RequestBody @Valid HobbyRequestDto.hobbyRecordDto,
            @PathVariable Long userId,
            @RequestHeader("Authorization") String authorizationHeader
    ){

    }
}
