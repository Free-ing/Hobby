package service.hobbyservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import service.hobbyservice.entity.HobbyRoutine;
import service.hobbyservice.repository.HobbyRoutineRepository;

@RequiredArgsConstructor
@Service
public class HobbyQueryServiceImpl implements HobbyQueryService {

    private final HobbyRoutineRepository hobbyRoutineRepository;

    @Override
    public HobbyRoutine findByHobbyNameAndUserId(String hobbyName, Long userId) {
        System.out.println(hobbyName);
        System.out.println(userId);
        return hobbyRoutineRepository.findByHobbyNameAndUserId(hobbyName, userId)
                .orElseThrow(() -> new RuntimeException("HobbyRoutine not found"));
    }
}