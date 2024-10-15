package service.hobbyservice.service;

import lombok.RequiredArgsConstructor;
import service.hobbyservice.converter.toEntity.HobbyConverter;
import service.hobbyservice.dto.request.HobbyRequestDto;
import service.hobbyservice.entity.HobbyRecord;
import service.hobbyservice.entity.HobbyRoutine;
import service.hobbyservice.repository.HobbyRecordRepository;

@RequiredArgsConstructor
public class HobbyCommonServiceImpl implements HobbyCommonService{

    private final HobbyQueryService hobbyQueryService;
    private final HobbyRecordRepository hobbyRecordRepository;

    @Override
    //Todo: 취미 기록하기
    public void createHobbyRecord(HobbyRequestDto.hobbyRecordDto hobbyRecordDto, Long userId){

        // 엔티티로 변환
        HobbyRecord hobbyRecord = HobbyConverter.toHobbyRecord(hobbyRecordDto);

        //루틴 객체 찾아오기
        HobbyRoutine hobbyRoutine = hobbyQueryService.findHobbyRoutineByNameAndUserId(hobbyRecordDto.getHobbyName(), userId);


        hobbyRecord.setHobbyRoutine(hobbyRoutine);

        hobbyRecordRepository.save(hobbyRecord);
    }


}
