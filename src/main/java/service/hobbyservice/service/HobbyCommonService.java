package service.hobbyservice.service;

import service.hobbyservice.dto.request.HobbyRequestDto;
import service.hobbyservice.dto.response.HobbyResponseDto;

import java.util.List;

public interface HobbyCommonService {
    //Todo: 취미 기록하기
    Long createHobbyRecord(HobbyRequestDto.hobbyRecordDto hobbyRecordDto, Long userId);

    //Todo: 취미 루틴 추가하기
    Long addHobbyRoutine(HobbyRequestDto.hobbyRoutineDto hobbyRoutineDto, Long userId);

    //Todo: 취미 기록 수정
    HobbyResponseDto.HobbyRecordDto updateHobbyRecord(HobbyRequestDto.hobbyRecordDto hobbyRecordDto, Long recordId, Long userId);


    //Todo: 취미 루틴 삭제
    void deleteHobbyRoutine(Long routineId, Long userId);

    //Todo: 취미 기록 삭제
    void deleteHobbyRecord(Long recordId, Long userId);

    //Todo: 회원의 모든 취미 기록 삭제
    void deleteHobbyData(Long userId);

    //Todo: 회원의 취미 루틴 수정
    void updateHobbyRoutine(HobbyRequestDto.hobbyRoutineDto hobbyRoutineDto, String imageUrl, Long routineId, Long userId);
}
