package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.History;

public interface HistoryRepository extends JpaRepository<History, Long> {

}
