package com.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.domain.History;
import com.repository.HistoryRepository;
import com.service.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public History addHistory(String description, MultipartFile imageFile, String email) throws IOException {
        History history = new History();
        history.setDescription(description);
        history.setImage(imageFile.getBytes());
        history.setEmail(email);
        history.setCreatedDate(LocalDateTime.now());
        return historyRepository.save(history);
    }

    @Override
    public List<History> getAllHistory() {
        return historyRepository.findAll();
    }

    @Override
    public Optional<History> getHistoryById(Long id) {
        return historyRepository.findById(id);
    }

    @Override
    public List<History> getAllHistories() {
        return historyRepository.findAll();
    }
}