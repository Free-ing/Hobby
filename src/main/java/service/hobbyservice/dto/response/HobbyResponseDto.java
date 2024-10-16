package service.hobbyservice.dto.response;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import service.hobbyservice.entity.HobbyRoutine;

public class HobbyResponseDto {


    //회원의 취미 루틴 리스트 반환에 사용되는 dtp
    @Getter
    @AllArgsConstructor
    @Builder
    public static class HobbyRoutineDto{
        private String HobbyName;
        private String imageUrl;
    }
}
