package service.hobbyservice.dto.response;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
        private Long routineId;

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

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class AlbumResponseDto{
        private LocalDate date;
        private String photoUrl;
        private String recordBody;
        private Long recordId;

    }
}
