package service.hobbyservice.repository;

import
        org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import service.hobbyservice.dto.response.HobbyResponseDto;
import service.hobbyservice.entity.HobbyRecord;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HobbyRecordRepository extends JpaRepository<HobbyRecord, Long> {

    Optional<HobbyRecord> findById(Long reocordId);

    @Query("SELECT hr FROM HobbyRecord hr WHERE YEAR(hr.createdAt) = :year AND MONTH(hr.createdAt) = :month AND hr.userId = :userId")
    List<HobbyRecord> findByYearAndMonth(@Param("year") int year, @Param("month") int month, Long userId);

}
