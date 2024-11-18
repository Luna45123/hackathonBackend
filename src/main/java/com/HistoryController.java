package com;

import java.io.IOException;

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

import java.util.*;
@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = "http://localhost:3000")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @PostMapping("/add")
    public ResponseEntity<History> addHistory(
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile imageFile) {
        try {
            History history = historyService.addHistory(description, imageFile);
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
}
