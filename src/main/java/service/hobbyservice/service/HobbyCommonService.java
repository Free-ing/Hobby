package service.hobbyservice.service;

import service.hobbyservice.dto.request.HobbyRequestDto;

public interface HobbyCommonService {
    //Todo: 취미 기록하기
    Long createHobbyRecord(HobbyRequestDto.hobbyRecordDto hobbyRecordDto, Long userId);

    //Todo: 취미 루틴 추가하기
    Long addHobbyRoutine(HobbyRequestDto.hobbyRoutineDto hobbyRoutineDto, Long userId);
}
