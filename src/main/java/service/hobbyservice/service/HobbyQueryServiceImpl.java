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
        return hobbyRoutineRepository.findByUserId(userId);
    }


    //Todo: 취미 이름과 userId로 취미 루틴 찾게 하는 건데 null로 반환 받아도 되는 메서드(취미 추가할 때 값이 null 이 아니면 추가하지 못하게끔)
    @Override
    public HobbyRoutine findByHobbyNameAndUserIdOrNull(String hobbyName, Long userId) {
        return hobbyRoutineRepository.findByHobbyNameAndUserIdOrNull(hobbyName,userId);
        }

    //    Todo: 앨범리스트 조회
    @Override
    public List<HobbyResponseDto.AlbumResponseDto> getAlbumList(int year, int month, Long userId) {
        return hobbyRecordRepository.findByYearAndMonth(year, month, userId)
                .stream()
                .map(hobbyRecord -> HobbyResponseDto.AlbumResponseDto.builder()
                        .date(hobbyRecord.getCreatedAt().toLocalDate())
                        .photoUrl(hobbyRecord.getPhotoUrl())
                        .recordBody(hobbyRecord.getRecordBody())
                        .recordId(hobbyRecord.getId())
                        .build())
                .collect(Collectors.toList());
    }
    }