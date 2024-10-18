package service.hobbyservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import service.hobbyservice.base.exception.code.RestApiException;
import service.hobbyservice.base.exception.code.RoutineErrorStatus;
import service.hobbyservice.converter.toEntity.HobbyConverter;
import service.hobbyservice.dto.request.HobbyRequestDto;
import service.hobbyservice.dto.response.HobbyResponseDto;
import service.hobbyservice.entity.HobbyRecord;
import service.hobbyservice.entity.HobbyRoutine;
import service.hobbyservice.repository.HobbyRecordRepository;
import service.hobbyservice.repository.HobbyRoutineRepository;

import static service.hobbyservice.converter.toDto.toDto.toHobbyRecordDto;

@RequiredArgsConstructor
@Service
public class HobbyCommonServiceImpl implements HobbyCommonService {

    private final HobbyQueryService hobbyQueryService;
    private final HobbyRecordRepository hobbyRecordRepository;
    private final HobbyRoutineRepository hobbyRoutineRepository;

    @Override
    //Todo: 취미 기록하기
    public Long createHobbyRecord(HobbyRequestDto.hobbyRecordDto hobbyRecordDto, Long userId) {

        // 엔티티로 변환
        HobbyRecord hobbyRecord = HobbyConverter.toHobbyRecord(hobbyRecordDto, userId);

        //루틴 객체 찾아오기
        HobbyRoutine hobbyRoutine = hobbyQueryService.findByHobbyNameAndUserId(hobbyRecordDto.getHobbyName(), userId);


        // 루틴 기록에 루틴 카테고리 설정
        hobbyRecord.setHobbyRoutine(hobbyRoutine);
        HobbyRecord savedRecord = hobbyRecordRepository.save(hobbyRecord);

        return savedRecord.getId();
    }

    //Todo: 취미 루틴 추가하기
    @Override
    public Long addHobbyRoutine(HobbyRequestDto.hobbyRoutineDto hobbyRoutineDto, Long userId) {

        // 추가하려고 하는 취미 루틴 조회해보기
        System.out.println(hobbyRoutineDto.getHobbyName());

        //취미가 있는지 조회하기
        HobbyRoutine findHobbyRoutine = hobbyQueryService.findByHobbyNameAndUserIdOrNull(hobbyRoutineDto.getHobbyName(), userId);

        //이미 존재하는지 확인해보기
        if (findHobbyRoutine != null) {
            throw new RestApiException(RoutineErrorStatus.HOBBY_ROUTINE_ALREADY_EXIST);
        } else {
            //존재하지 않는 경우
            HobbyRoutine hobbyRoutine = HobbyConverter.toHobbyRoutine(hobbyRoutineDto, userId);
            HobbyRoutine saveHobbyRoutine = hobbyRoutineRepository.save(hobbyRoutine);
            return saveHobbyRoutine.getId();
        }
    }

    //Todo: 취미 기록 수정
    @Override
    public HobbyResponseDto.HobbyRecordDto updateHobbyRecord(HobbyRequestDto.hobbyRecordDto hobbyRecordDto, Long recordId,Long userId){

        //취미 기록 불러오기
        HobbyRecord hobbyRecord = hobbyRecordRepository.findById(recordId)
                .orElseThrow(() -> new RestApiException(RoutineErrorStatus.RECORD_NOT_FOUND));

        // 사용자가 원하는 취미 루틴 불러오기
        HobbyRoutine hobbyRoutine = hobbyRoutineRepository.findByHobbyNameAndUserId(hobbyRecordDto.getHobbyName(), userId)
                .orElseThrow(() -> new RestApiException(RoutineErrorStatus.HOBBY_ROUTINE_NOT_FOUND));

        //취미 업데이트
        hobbyRecord.updateRecord(hobbyRoutine,hobbyRecordDto.getHobbyBody(),hobbyRecordDto.getPhotoUrl());

        //취미 반환
        return toHobbyRecordDto(hobbyRecord, hobbyRoutine);

    }

    //Todo: 취미 루틴 삭제
    @Override
    public void deleteHobbyRoutine(Long routineId, Long userId){
        HobbyRoutine hobbyRoutine = hobbyRoutineRepository.findByIdAndUserId(routineId,userId)
                .orElseThrow(() -> new RestApiException(RoutineErrorStatus.USER_CANT_DELETE));

        hobbyRoutineRepository.delete(hobbyRoutine);
    }

    //Todo: 취미 기록 삭제
    @Override
    public void deleteHobbyRecord(Long recordId, Long userId){
        HobbyRecord hobbyRecord = hobbyRecordRepository.findByIdAndUserId(recordId,userId)
                .orElseThrow(() -> new RestApiException(RoutineErrorStatus.USER_CANT_DELETE));

        hobbyRecordRepository.delete(hobbyRecord);
    }


}



