package service.hobbyservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import service.hobbyservice.base.exception.code.RestApiException;
import service.hobbyservice.base.exception.code.RoutineErrorStatus;
import service.hobbyservice.dto.response.HobbyResponseDto;
import service.hobbyservice.entity.HobbyRoutine;
import service.hobbyservice.repository.HobbyRecordRepository;
import service.hobbyservice.repository.HobbyRoutineRepository;

import java.util.List;

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

    @Override
    public List<HobbyResponseDto.HobbyRoutineDto> getHobbyRoutineListByUserId(Long userId) {
        return hobbyRoutineRepository.findByUserId(userId);
    }


    @Override
    public HobbyRoutine findByHobbyNameAndUserIdOrNull(String hobbyName, Long userId) {
        return hobbyRoutineRepository.findByHobbyNameAndUserIdOrNull(hobbyName,userId);
        }
    }