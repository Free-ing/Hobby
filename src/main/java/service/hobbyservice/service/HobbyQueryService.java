package service.hobbyservice.service;

import org.springframework.transaction.annotation.Transactional;
import service.hobbyservice.dto.response.HobbyResponseDto;
import service.hobbyservice.dto.response.RoutineTrackerDto;
import service.hobbyservice.entity.HobbyRoutine;

import java.time.LocalDate;
import java.util.List;

public interface HobbyQueryService {

    HobbyRoutine findByHobbyNameAndUserId(String hobbyName, Long userId);

    //Todo: userId로 회원의 취미 루틴 LIst 조회
    List<HobbyResponseDto.HobbyRoutineDto> getHobbyRoutineListByUserId(Long userId);


    HobbyRoutine findByHobbyNameAndUserIdOrNull(String hobbyName, Long userId);

    //    Todo: 앨범리스트 조회
    List<HobbyResponseDto.AlbumResponseDto> getAlbumList(int year, int month, Long userId);

    //Todo: 취미 루틴 트래커 조회
    List<RoutineTrackerDto.HobbyRoutineTrackerDto> getHobbyRoutineTrackers(Long userId, int year, int month);

    //Todo: 홈화면 하나라도 수행한 날짜 반환
    @Transactional(readOnly = true)
    List<HobbyResponseDto.DayCompleteRoutine> getCompleteDate(LocalDate startDate, LocalDate endDate, Long userId);
}
