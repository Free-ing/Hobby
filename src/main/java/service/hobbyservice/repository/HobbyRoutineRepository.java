package service.hobbyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import service.hobbyservice.dto.response.HobbyResponseDto;
import service.hobbyservice.dto.response.RoutineTrackerDto;
import service.hobbyservice.entity.HobbyRoutine;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HobbyRoutineRepository extends JpaRepository<HobbyRoutine,Long>{
    Optional<HobbyRoutine> findByHobbyNameAndUserId(String hobbyName, Long userId);

    @Query("SELECT hr FROM HobbyRoutine hr WHERE hr.hobbyName = :hobbyName AND hr.userId = :userId")
    HobbyRoutine findByHobbyNameAndUserIdOrNull(@Param("hobbyName") String hobbyName, @Param("userId") Long userId);

    @Query("SELECT new service.hobbyservice.dto.response.HobbyResponseDto$HobbyRoutineDto(hr.hobbyName, hr.imageUrl, hr.id) FROM HobbyRoutine hr WHERE hr.userId = :userId")
    List<HobbyResponseDto.HobbyRoutineDto> findHobbyRoutineDtoByUserId(@Param("userId") Long userId);

    @Query("SELECT hr FROM HobbyRoutine hr WHERE hr.userId =:userId")
    List<HobbyRoutine> findHobbyRoutineLIstByUserId(Long userId);

    //routineId와 userId로 특정 루틴 조회(검증을 위해 이 두개로 조회함)
    Optional<HobbyRoutine> findByIdAndUserId(Long Id, Long userId);

//    Optional<HobbyRoutine> findByIdAndUserId(Long id, Long userId);

//    @Query("SELECT new service.hobbyservice.dto.response.HobbyResponseDto$HobbyRoutineDto(hr.id, hr.hobbyName, hr.imageUrl) FROM HobbyRoutine hr WHERE hr.userId = :userId")
//    List<HobbyResponseDto.HobbyRoutineDto> findByUserId(@Param("userId") Long userId);

    @Query("SELECT h FROM HobbyRoutine h " +
            "LEFT JOIN FETCH h.hobbyRecordList hr " +
            "WHERE h.userId = :userId " +
            "AND (hr IS NULL OR (YEAR(hr.routineDate) = :year AND MONTH(hr.routineDate) = :month))")
    List<HobbyRoutine> findAllWithRecordsByUserId(
            @Param("userId") Long userId,
            @Param("year") int year,
            @Param("month") int month
    );

}
