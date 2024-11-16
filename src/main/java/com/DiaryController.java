package com;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.domain.Customer;
import com.domain.Diary;
import com.dto.CustomerDTO;
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
    public ResponseEntity<String> createDiary(@RequestBody DiaryDTO diaryDTO) {
        Diary newDiary = new Diary();
        diaryMapper.updateDiaryFromDto(diaryDTO, newDiary);
        diaryRepository.save(newDiary);
        return new ResponseEntity<String>("Diary created", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<DiaryDTO>> getAllDiary() {
        List<Diary> diaries = diaryRepository.findAll();
        List<DiaryDTO> diaryDTOs = new ArrayList<DiaryDTO>();
        diaryMapper.updateDiaryFromEntity(diaries, diaryDTOs);
        return new ResponseEntity<Collection<DiaryDTO>>(diaryDTOs, HttpStatus.OK);
    }

    @GetMapping("/Diary/{id}")
    public ResponseEntity<DiaryDTO> getDiaryById(@PathVariable Long id) {
        if (!diaryRepository.existsById(id))
            return new ResponseEntity<DiaryDTO>(HttpStatus.NOT_FOUND);
        Optional<Diary> customer = diaryRepository.findById(id);
        DiaryDTO dto = new DiaryDTO();
        diaryMapper.updateDiaryFromEntity(customer.get(), dto);
        return new ResponseEntity<DiaryDTO>(dto, HttpStatus.OK);
    }

    @GetMapping("/diary/user/{userId}")
    public ResponseEntity<Collection<DiaryDTO>> getDiaryByUserId(@PathVariable Long userId){
        Collection<Diary> diaries = diaryRepository.findByUserId(userId);
        List<DiaryDTO> diaryDTOs = new ArrayList<>();
        diaryMapper.updateDiaryFromEntity(diaries, diaryDTOs);
        return new ResponseEntity<Collection<DiaryDTO>>(diaryDTOs,HttpStatus.OK);
    }


    @PatchMapping("/Diary/{id}")
    public ResponseEntity<String> updateDiary(@PathVariable Long id, @RequestBody DiaryDTO diaryDTO) {
        if (!diaryRepository.existsById(id)) {
            return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
        }
        Diary diary = diaryRepository.findById(id).orElseThrow();

        // Ensure the DTO ID matches the entity ID or reset it to avoid altering the ID
        diaryDTO.setId(id);

        // Map fields, excluding the ID, to prevent Hibernate's error
        diaryMapper.updateDiaryFromDto(diaryDTO, diary);

        diaryRepository.save(diary);
        return new ResponseEntity<>("Diary updated", HttpStatus.OK);
    }

    @DeleteMapping("Diary/{id}")
    public ResponseEntity<String> deleteDiary(@PathVariable Long id) {
        diaryRepository.deleteById(id);
        return new ResponseEntity<String>("diary deleted", HttpStatus.OK);
    }

}
