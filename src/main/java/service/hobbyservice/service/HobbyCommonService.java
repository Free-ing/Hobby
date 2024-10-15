package service.hobbyservice.service;

import service.hobbyservice.dto.request.HobbyRequestDto;

public interface HobbyCommonService {
    //Todo: 취미 기록하기
    void createHobbyRecord(HobbyRequestDto.hobbyRecordDto hobbyRecordDto, Long userId);
}
