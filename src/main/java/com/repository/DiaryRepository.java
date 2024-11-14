package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.domain.Diary;

public interface DiaryRepository extends CrudRepository<Diary,Long>{
    @SuppressWarnings("null")
    List<Diary> findAll();
}
