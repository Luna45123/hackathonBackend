package com;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.Challenge;
import com.dto.ChallengeDTO;
import com.service.ChallengeService;

@RestController
@RequestMapping("api/challenge")
@CrossOrigin(origins = "*")
public class ChallengeController {
    @Autowired
    private ChallengeService challengeService;

    @PostMapping(path = "/save")
    public String saveChallenge(@RequestBody ChallengeDTO challengeDTO) {
        String id = challengeService.addChallenge(challengeDTO);
        return id;
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteChallenge(@PathVariable int id) {
        challengeService.deleteChallenge(id);
        return ResponseEntity.ok("Challenge deleted successfully");
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Challenge> getChallengeById(@PathVariable int id) {
        Challenge challenge = challengeService.getChallengeById(id);
        if (challenge != null) {
            return ResponseEntity.ok(challenge);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Challenge>> getAllChallenges() {
        List<Challenge> challenges = challengeService.getAllChallenges();
        if (!challenges.isEmpty()) {
            return ResponseEntity.ok(challenges);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<String> updateChallenge(@PathVariable int id, @RequestBody ChallengeDTO challengeDTO) {
        boolean updated = challengeService.updateChallenge(id, challengeDTO);
        if (updated) {
            return ResponseEntity.ok("Challenge updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(path = "/update/{id}")
    public ResponseEntity<String> partialUpdateChallenge(@PathVariable int id,
            @RequestBody Map<String, Object> updates) {
        boolean updated = challengeService.partialUpdateChallenge(id, updates);
        if (updated) {
            return ResponseEntity.ok("Challenge updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
