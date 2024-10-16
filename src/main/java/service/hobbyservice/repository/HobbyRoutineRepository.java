package service.hobbyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import service.hobbyservice.entity.HobbyRoutine;

import java.util.List;
import java.util.Optional;

@Repository
public interface HobbyRoutineRepository extends JpaRepository<HobbyRoutine,Long>{
    Optional<HobbyRoutine> findByHobbyNameAndUserId(String hobbyName, Long userId);
    List<HobbyRoutine> findByUserId(Long userId);


}
