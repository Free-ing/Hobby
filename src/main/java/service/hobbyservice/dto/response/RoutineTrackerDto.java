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
        private List<HobbyRecordDto> records = new ArrayList<>();

        public HobbyRoutineTrackerDto(String hobbyName) {
        this.hobbyName = hobbyName;
        }
        public void addRecord(HobbyRecordDto record) {
            this.records.add(record);
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class HobbyRecordDto {
        private Long id;
        private LocalDate createdAt;  // LocalDateTime 대신 LocalDate 사용

        public HobbyRecordDto(Long id, LocalDateTime createdAt) {
            this.id = id;
            this.createdAt = createdAt.toLocalDate();  // LocalDate로 변환
        }
    }
}
