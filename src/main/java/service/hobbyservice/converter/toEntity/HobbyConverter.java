package service.hobbyservice.converter.toEntity;

import service.hobbyservice.dto.request.HobbyRequestDto;
import service.hobbyservice.entity.HobbyRecord;

public class HobbyConverter {

    public static HobbyRecord toHobbyRecord(HobbyRequestDto.hobbyRecordDto hobbyRecordDto){
        return HobbyRecord.builder()
                .iamgeUrl(hobbyRecordDto.getImageUrl())
                .recordBody(hobbyRecordDto.getHobbyBody())
                .build();
       }
}
