package service.hobbyservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.hobbyservice.base.exception.code.RestApiException;
import service.hobbyservice.base.exception.code.RoutineErrorStatus;
import service.hobbyservice.dto.response.HobbyResponseDto;
import service.hobbyservice.dto.response.RoutineTrackerDto;
import service.hobbyservice.entity.HobbyRecord;
import service.hobbyservice.entity.HobbyRoutine;
import service.hobbyservice.repository.HobbyRecordRepository;
import service.hobbyservice.repository.HobbyRoutineRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HobbyQueryServiceImpl implements HobbyQueryService {

    private final HobbyRoutineRepository hobbyRoutineRepository;
    private final HobbyRecordRepository hobbyRecordRepository;

    //Todo: 취미와 user의 id로 취미 루틴 찾기
    @Override
    public HobbyRoutine findByHobbyNameAndUserId(String hobbyName, Long userId) {
        return hobbyRoutineRepository.findByHobbyNameAndUserId(hobbyName, userId)
                .orElseThrow(() -> new RestApiException(RoutineErrorStatus.HOBBY_ROUTINE_NOT_FOUND));
    }

    //Todo: userId로 회원의 취미 루틴 LIst 조회
//    @Override
//    public List<HobbyResponseDto.HobbyRoutineDto> getHobbyRoutineListByUserId(Long userId){
//        List<HobbyResponseDto.HobbyRoutineDto> hobbyRoutineList = hobbyRoutineRepository.findByUserId(userId);
//        return hobbyRoutineList.stream()
//                .map(routine -> new HobbyResponseDto.HobbyRoutineDto(routine.getHobbyName(), routine.getImageUrl()))
//                .collect(Collectors.toList());
//    }

    //Todo: userId로 회원 취미 루틴 List 조회
    @Override
    public List<HobbyResponseDto.HobbyRoutineDto> getHobbyRoutineListByUserId(Long userId) {
        return hobbyRoutineRepository.findHobbyRoutineDtoByUserId(userId);
    }


    //Todo: 취미 이름과 userId로 취미 루틴 찾게 하는 건데 null로 반환 받아도 되는 메서드(취미 추가할 때 값이 null 이 아니면 추가하지 못하게끔)
    @Override
    public HobbyRoutine findByHobbyNameAndUserIdOrNull(String hobbyName, Long userId) {
        return hobbyRoutineRepository.findByHobbyNameAndUserIdOrNull(hobbyName,userId);
        }

    //    Todo: 앨범리스트 조회
    @Override
    @Transactional(readOnly = true)
    public List<HobbyResponseDto.AlbumResponseDto> getAlbumList(int year, int month, Long userId) {
        return hobbyRecordRepository.findByYearAndMonth(year, month, userId)
                .stream()
                .map(hobbyRecord -> HobbyResponseDto.AlbumResponseDto.builder()
                        .date(hobbyRecord.getRoutineDate())
                        .hobbyName(hobbyRecord.getHobbyRoutine().getHobbyName())
                        .photoUrl(hobbyRecord.getPhotoUrl())
                        .recordBody(hobbyRecord.getRecordBody())
                        .recordId(hobbyRecord.getId())
                        .build())
                .collect(Collectors.toList());
    }

    //Todo: 취미 루틴 트래커 조회
    @Override
    public List<RoutineTrackerDto.HobbyRoutineTrackerDto> getHobbyRoutineTrackers(Long userId, int year, int month) {
        Map<String, RoutineTrackerDto.HobbyRoutineTrackerDto> routineMap = new LinkedHashMap<>();
        List<HobbyRoutine> routines = hobbyRoutineRepository.findAllWithRecordsByUserId(userId, year, month);

        for (HobbyRoutine routine : routines) {
            if (!routine.getHobbyRecordList().isEmpty()) {  // 레코드가 있는 경우만 처리
                RoutineTrackerDto.HobbyRoutineTrackerDto trackerDto =
                        routineMap.computeIfAbsent(routine.getHobbyName(),
                                k -> {
                                    RoutineTrackerDto.HobbyRoutineTrackerDto dto =
                                            new RoutineTrackerDto.HobbyRoutineTrackerDto(routine.getHobbyName());
                                    dto.setImageUrl(routine.getImageUrl()); // 이미지 URL 설정
                                    return dto;
                                });
                for (HobbyRecord record : routine.getHobbyRecordList()) {
                    trackerDto.addRecord(new RoutineTrackerDto.HobbyRecordDto(
                            record.getId(),
                            record.getRoutineDate()
                    ));
                }
            }
        }

        return new ArrayList<>(routineMap.values());
    }

    //Todo: 홈화면 하나라도 수행한 날짜 반환
    @Override
    @Transactional(readOnly = true)
    public List<HobbyResponseDto.DayCompleteRoutine> getCompleteDate(LocalDate startDate, LocalDate endDate, Long userId) {
        return hobbyRecordRepository.findCompletedDatesByUserIdAndDateRange(userId, startDate, endDate);
    }
}