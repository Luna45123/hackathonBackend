package com;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.service.HistoryService;
import com.domain.History;
import com.repository.HistoryRepository;

import java.util.*;

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = "http://localhost:3000")

public class HistoryController {

    @Autowired
    private HistoryService historyService;
    @Autowired
    private HistoryRepository historyRepository;

    @PostMapping("/add")
    public ResponseEntity<History> addHistory(
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile imageFile,
            @RequestParam("email") String email) { // ตรวจสอบว่ารับ email หรือไม่
        try {
            if (description == null || email == null || imageFile.isEmpty()) {
                return ResponseEntity.badRequest().body(null); // ส่ง 400 หากข้อมูลไม่ครบ
            }

            // สร้าง History
            History history = historyService.addHistory(description, imageFile, email);
            return ResponseEntity.ok(history);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/submitted")
    public ResponseEntity<List<History>> getSubmittedChallenges() {
        List<History> historyList = historyService.getAllHistory();
        return ResponseEntity.ok(historyList);
    }

    @GetMapping("/all")
    public ResponseEntity<List<History>> getAllHistory() {
        List<History> histories = historyService.getAllHistory();
        return ResponseEntity.ok(histories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<History> getHistoryById(@PathVariable Long id) {
        Optional<History> history = historyService.getHistoryById(id);
        return history.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<History>> getHistoryByEmail(@RequestParam String email) {
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // ดึงข้อมูลจากฐานข้อมูลโดยใช้ email
        List<History> historyList = historyRepository.findByEmail(email);
        if (historyList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(historyList);
    }

    @GetMapping("/status/{email}")
    public ResponseEntity<Boolean> isChallengeCompletedToday(@PathVariable String email) {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);

        List<History> histories = historyRepository.findHistoryByEmailAndDate(email, startOfDay, endOfDay);
        boolean isCompletedToday = !histories.isEmpty();

        return ResponseEntity.ok(isCompletedToday);
    }

}