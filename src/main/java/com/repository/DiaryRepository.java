package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.domain.Diary;

@Repository
public interface DiaryRepository extends CrudRepository<Diary,Long>{
    @SuppressWarnings("null")
    List<Diary> findAll();

    public List<Diary> findByUserId(Long userId);
    // Custom query to find diaries by user email
    @Query("SELECT d FROM Diary d WHERE d.userId IN (SELECT u.id FROM User u WHERE u.email = :email)")
    List<Diary> findByUserEmail(String email);
}
