package service.hobbyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.hobbyservice.entity.HobbyRecord;
import service.hobbyservice.entity.HobbyRoutine;

@Repository
public interface HobbyRecordRepository extends JpaRepository<HobbyRecord, Long> {
}
