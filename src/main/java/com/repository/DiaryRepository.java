package com.repository;

import org.springframework.data.repository.CrudRepository;

import com.domain.Diary;

public interface DiaryRepository extends CrudRepository<Diary,Long>{

}
