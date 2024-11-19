package com.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.domain.History;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByEmail(String email);

    @Query("SELECT h FROM History h WHERE h.email = :email AND h.createdDate >= :startOfDay AND h.createdDate < :endOfDay")
    List<History> findHistoryByEmailAndDate(@Param("email") String email, @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay);
}
