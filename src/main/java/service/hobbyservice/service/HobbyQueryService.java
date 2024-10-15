package service.hobbyservice.service;

import service.hobbyservice.entity.HobbyRoutine;

public interface HobbyQueryService {
    HobbyRoutine findHobbyRoutineByNameAndUserId(String hobbyName, Long userId);
}
