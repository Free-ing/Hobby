package service.hobbyservice.service;

import service.hobbyservice.entity.HobbyRoutine;

public interface HobbyQueryService {

    HobbyRoutine findByHobbyNameAndUserId(String hobbyName, Long userId);

}
