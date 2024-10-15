package service.hobbyservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import service.hobbyservice.converter.toEntity.HobbyConverter;
import service.hobbyservice.dto.request.HobbyRequestDto;
import service.hobbyservice.entity.HobbyRecord;
import service.hobbyservice.entity.HobbyRoutine;
import service.hobbyservice.repository.HobbyRecordRepository;

@RequiredArgsConstructor
@Service
public class HobbyCommonServiceImpl implements HobbyCommonService{

    private final HobbyQueryService hobbyQueryService;
    private final HobbyRecordRepository hobbyRecordRepository;

    @Override
    //Todo: 취미 기록하기
    public Long createHobbyRecord(HobbyRequestDto.hobbyRecordDto hobbyRecordDto, Long userId){

        // 엔티티로 변환
        HobbyRecord hobbyRecord = HobbyConverter.toHobbyRecord(hobbyRecordDto,userId);

        //루틴 객체 찾아오기
        HobbyRoutine hobbyRoutine = hobbyQueryService.findByHobbyNameAndUserId(hobbyRecordDto.getHobbyName(), userId);


        // 루틴 기록에 루틴 카테고리 설장
        hobbyRecord.setHobbyRoutine(hobbyRoutine);
        HobbyRecord savedRecord = hobbyRecordRepository.save(hobbyRecord);

        return savedRecord.getId();
    }


}
