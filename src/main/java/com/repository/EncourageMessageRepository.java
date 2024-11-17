package com.repository;

import com.domain.EncourageMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncourageMessageRepository extends JpaRepository<EncourageMessage, Long> {
}
