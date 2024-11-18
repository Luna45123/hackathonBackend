package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.History;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByEmail(String email);
}
