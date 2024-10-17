package service.hobbyservice.service;

import service.hobbyservice.dto.response.HobbyResponseDto;
import service.hobbyservice.entity.HobbyRoutine;

import java.util.List;

public interface HobbyQueryService {

    HobbyRoutine findByHobbyNameAndUserId(String hobbyName, Long userId);

    //Todo: userId로 회원의 취미 루틴 LIst 조회
    List<HobbyResponseDto.HobbyRoutineDto> getHobbyRoutineListByUserId(Long userId);


    HobbyRoutine findByHobbyNameAndUserIdOrNull(String hobbyName, Long userId);
}
