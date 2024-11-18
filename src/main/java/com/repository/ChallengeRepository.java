package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, Integer> {

}
