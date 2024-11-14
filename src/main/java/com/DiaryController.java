package com;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.domain.Diary;
import com.dto.DiaryDTO;
import com.mapper.DiaryMapper;
import com.repository.DiaryRepository;

@RestController
public class DiaryController {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    DiaryMapper diaryMapper;

    @PostMapping("/addDiary")
    public ResponseEntity<String> createDiary(@RequestBody DiaryDTO diaryDTO){
        Diary newDiary = new Diary();
        diaryMapper.updateDiaryFromDto(diaryDTO, newDiary);
        diaryRepository.save(newDiary);
        return new ResponseEntity<String>("Diary created",HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<DiaryDTO>> getAllDiary(){
       return null;
    }

}
