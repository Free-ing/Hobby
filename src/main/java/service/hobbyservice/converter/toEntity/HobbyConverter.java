package service.hobbyservice.converter.toEntity;

import service.hobbyservice.dto.request.HobbyRequestDto;
import service.hobbyservice.entity.HobbyRecord;

public class HobbyConverter {


    // dto를 record 객체로 변환
    public static HobbyRecord toHobbyRecord(HobbyRequestDto.hobbyRecordDto hobbyRecordDto,Long userId){
        return HobbyRecord.builder()
                .recordBody(hobbyRecordDto.getHobbyBody())
                .userId(userId)
                .imageUrl(hobbyRecordDto.getImageUrl())
                .build();
       }
}
