package com.service;

import java.util.List;
import java.util.Map;

import com.domain.Challenge;
import com.dto.ChallengeDTO;

public interface ChallengeService {
    String addChallenge(ChallengeDTO challengeDTO);
    void deleteChallenge(int id);
    Challenge getChallengeById(int id);
    boolean updateChallenge(int id, ChallengeDTO challengeDTO);
    boolean partialUpdateChallenge(int id, Map<String, Object> updates);
    List<Challenge> getAllChallenges();
}