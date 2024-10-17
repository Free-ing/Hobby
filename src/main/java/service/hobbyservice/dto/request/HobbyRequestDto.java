package service.hobbyservice.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import service.hobbyservice.entity.HobbyRoutine;

import java.util.ArrayList;
import java.util.List;

public class HobbyRequestDto {
    @Getter
    @AllArgsConstructor
    @Builder
    public static class hobbyRecordDto {
        @NotEmpty(message = "취미의 이름을 정해주세요.")
        private String hobbyName;

        private String photoUrl;

        private String hobbyBody;

    }


    @Getter
    @AllArgsConstructor
    @Builder
    public static class hobbyRoutineDto{
        @NotEmpty(message = "취미의 이름을 정해주세요.")
        private String hobbyName;

        private String imageUrl;

        private String explanation;
    }


}
