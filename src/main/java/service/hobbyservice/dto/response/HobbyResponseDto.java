package service.hobbyservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import service.hobbyservice.entity.HobbyRoutine;

import java.time.LocalDate;
import java.util.List;

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
        private String hobbyName;
        private String photoUrl;
        private String recordBody;
        private Long recordId;

    }

    @JsonProperty("recommendations")
    @Getter
    private List<AiHobbyResponseDto> AiRecommendations;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class AiHobbyResponseDto{
        private String hobbyName;
        private String explanation;

    }

    @Getter
    @NoArgsConstructor
    @Builder
    public static class DayCompleteRoutine {
        private LocalDate completeDate;

        // JPQL new 연산자를 위한 생성자 추가
        public DayCompleteRoutine(LocalDate completeDate) {
            this.completeDate = completeDate;
        }
    }
}
