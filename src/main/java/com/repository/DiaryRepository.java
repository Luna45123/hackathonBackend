package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.domain.Diary;

@Repository
public interface DiaryRepository extends CrudRepository<Diary,Long>{
    @SuppressWarnings("null")
    List<Diary> findAll();

    public List<Diary> findByUserId(Long userId);
}
