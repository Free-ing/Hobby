package service.hobbyservice.dto.response;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import service.hobbyservice.entity.HobbyRoutine;

import java.time.LocalDate;

public class HobbyResponseDto {


    //회원의 취미 루틴 리스트 반환에 사용되는 dto
    @Getter
    @AllArgsConstructor
    @Builder
    public static class HobbyRoutineDto{
        private String hobbyName;
        private String imageUrl;

    }

    //회원의 취미 기록 반환에 사용되는 dto
    @Getter
    @AllArgsConstructor
    @Builder
    public static class HobbyRecordDto{
        private String hobbyName;

        private String photoUrl;

        private String hobbyBody;
    }

    public static class AlbumResponseDto{
        private LocalDate date;
        private String photo;
        private String hobbyBody;
    }
}
