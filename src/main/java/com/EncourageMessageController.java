package com;

import com.domain.EncourageMessage;
import com.dto.EncourageMessageDTO;
import com.mapper.EncourageMessageMapper;
import com.repository.EncourageMessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EncourageMessageController {

    @Autowired
    private EncourageMessageRepository encourageMessageRepository;

    @Autowired
    private EncourageMessageMapper encourageMessageMapper;

    // Create new message
    @PostMapping("/encourageMessage")
    public ResponseEntity<EncourageMessageDTO> createEncourageMessage(@RequestBody EncourageMessageDTO dto) {
        EncourageMessage message = encourageMessageMapper.toEncourageMessage(dto);
        encourageMessageRepository.save(message);
        return new ResponseEntity<>(encourageMessageMapper.toEncourageMessageDTO(message), HttpStatus.CREATED);
    }

    // Get all messages
    @GetMapping("/encourageMessages")
    public ResponseEntity<List<EncourageMessageDTO>> getAllEncourageMessages() {
        List<EncourageMessage> messages = encourageMessageRepository.findAll();
        List<EncourageMessageDTO> dtos = encourageMessageMapper.toEncourageMessageDTOList(messages);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    // Get a specific message by ID
    @GetMapping("/encourageMessage/{id}")
    public ResponseEntity<EncourageMessageDTO> getEncourageMessageById(@PathVariable Long id) {
        return encourageMessageRepository.findById(id)
                .map(message -> new ResponseEntity<>(encourageMessageMapper.toEncourageMessageDTO(message), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update a message by ID
    @PutMapping("/encourageMessage/{id}")
    public ResponseEntity<EncourageMessageDTO> updateEncourageMessage(
            @PathVariable Long id, @RequestBody EncourageMessageDTO dto) {
        return encourageMessageRepository.findById(id)
                .map(existingMessage -> {
                    encourageMessageMapper.updateEncourageMessageFromDto(dto, existingMessage);
                    encourageMessageRepository.save(existingMessage);
                    return new ResponseEntity<>(encourageMessageMapper.toEncourageMessageDTO(existingMessage), HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete a message by ID
    @DeleteMapping("/encourageMessage/{id}")
    public ResponseEntity<Void> deleteEncourageMessage(@PathVariable Long id) {
        return encourageMessageRepository.findById(id)
                .map(message -> {
                    encourageMessageRepository.delete(message);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
