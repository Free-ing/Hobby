package service.hobbyservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import service.hobbyservice.entity.HobbyRoutine;
import service.hobbyservice.repository.HobbyRoutineRepository;

@RequiredArgsConstructor
public class HobbyQueryServiceImpl implements HobbyQueryService{

    HobbyRoutineRepository hobbyRoutineRepository;

    @Override
    public HobbyRoutine findHobbyRoutineByNameAndUserId(String hobbyName, Long userId) {
        return hobbyRoutineRepository.findByHobbyNameAndUserId(hobbyName, userId)
                .orElseThrow(() -> new RuntimeException("HobbyRoutine not found"));
    }
}
