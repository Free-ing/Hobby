package service.hobbyservice.converter.toEntity;

import service.hobbyservice.dto.request.HobbyRequestDto;
import service.hobbyservice.dto.response.HobbyResponseDto;
import service.hobbyservice.entity.HobbyRecord;
import service.hobbyservice.entity.HobbyRoutine;

import java.time.LocalDate;

public class HobbyConverter {


    // dto를 record 객체로 변환
    public static HobbyRecord toHobbyRecord(HobbyRequestDto.hobbyRecordDto hobbyRecordDto, Long userId, LocalDate date) {
        return HobbyRecord.builder()
                .recordBody(hobbyRecordDto.getHobbyBody())
                .userId(userId)
                .routineDate(date)
                .photoUrl(hobbyRecordDto.getPhotoUrl())
                .build();
    }

    public static HobbyRoutine toHobbyRoutine(HobbyRequestDto.hobbyRoutineDto hobbyRoutineDto,Long userId){
        return HobbyRoutine.builder()
                .hobbyName(hobbyRoutineDto.getHobbyName())
//                .explanation(hobbyRoutineDto.getExplanation())
                .imageUrl(hobbyRoutineDto.getImageUrl())
                .userId(userId)
                .build();
    }

}
