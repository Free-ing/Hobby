package service.hobbyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import service.hobbyservice.dto.response.HobbyResponseDto;
import service.hobbyservice.entity.HobbyRoutine;

import java.util.List;
import java.util.Optional;

@Repository
public interface HobbyRoutineRepository extends JpaRepository<HobbyRoutine,Long>{
    Optional<HobbyRoutine> findByHobbyNameAndUserId(String hobbyName, Long userId);

    @Query("SELECT hr FROM HobbyRoutine hr WHERE hr.hobbyName = :hobbyName AND hr.userId = :userId")
    HobbyRoutine findByHobbyNameAndUserIdOrNull(@Param("hobbyName") String hobbyName, @Param("userId") Long userId);

    @Query("SELECT new service.hobbyservice.dto.response.HobbyResponseDto$HobbyRoutineDto(hr.hobbyName, hr.imageUrl, hr.id) FROM HobbyRoutine hr WHERE hr.userId = :userId")
    List<HobbyResponseDto.HobbyRoutineDto> findByUserId(@Param("userId") Long userId);

//    Optional<HobbyRoutine> findByIdAndUserId(Long id, Long userId);

//    @Query("SELECT new service.hobbyservice.dto.response.HobbyResponseDto$HobbyRoutineDto(hr.id, hr.hobbyName, hr.imageUrl) FROM HobbyRoutine hr WHERE hr.userId = :userId")
//    List<HobbyResponseDto.HobbyRoutineDto> findByUserId(@Param("userId") Long userId);

}
