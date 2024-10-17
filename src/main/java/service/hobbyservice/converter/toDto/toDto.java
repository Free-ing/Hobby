package service.hobbyservice.converter.toDto;

import service.hobbyservice.dto.response.HobbyResponseDto;
import service.hobbyservice.entity.HobbyRecord;
import service.hobbyservice.entity.HobbyRoutine;

public class toDto {


    //record 객체를 Dto로 변환화는 메서드이다
    public static HobbyResponseDto.HobbyRecordDto toHobbyRecordDto(HobbyRecord hobbyRecord, HobbyRoutine hobbyRoutine){
        return HobbyResponseDto.HobbyRecordDto.builder()
                .hobbyBody(hobbyRecord.getRecordBody())
                .hobbyName(hobbyRoutine.getHobbyName())
                .photoUrl(hobbyRecord.getPhotoUrl())
                .build();
    }

}
