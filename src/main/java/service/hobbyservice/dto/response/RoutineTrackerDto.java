package service.hobbyservice.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RoutineTrackerDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class HobbyRoutineTrackerDto {
        private String hobbyName;
        private String imageUrl;
        private List<HobbyRecordDto> records = new ArrayList<>();

        public HobbyRoutineTrackerDto(String hobbyName) {
        this.hobbyName = hobbyName;
        }
        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
        public void addRecord(HobbyRecordDto record) {
            this.records.add(record);
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class HobbyRecordDto {
        private Long id;
        private LocalDate routineDate;  // LocalDateTime 대신 LocalDate 사용

        public HobbyRecordDto(Long id, LocalDate routineDate) {
            this.id = id;
            this.routineDate = routineDate;  // LocalDate로 변환
        }
    }
}
