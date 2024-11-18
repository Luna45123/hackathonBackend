package com;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.Encorage;
import com.dto.EncorageDTO;
import com.service.EncorageService;

@RestController
@RequestMapping("/api/encorage")
@CrossOrigin(origins = "http://localhost:3000")
public class EncorageController {
    

    @Autowired
    private EncorageService encorageService;

    @PostMapping("/add")
    public ResponseEntity<String> addEncorage(@RequestBody EncorageDTO encorageDTO) {
        String response = encorageService.addEncorage(encorageDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Encorage>> getAllEncorage() {
    List<Encorage> encorage = encorageService.getAllEncorage();
    if (!encorage.isEmpty()) {
        return ResponseEntity.ok(encorage);
    } else {
        return ResponseEntity.noContent().build();
    }
}

    @GetMapping("/{id}")
    public ResponseEntity<Encorage> getEncorageById(@PathVariable Long id) {
        Encorage encorage = encorageService.getEncorageById(id);
        if (encorage != null) {
            return ResponseEntity.ok(encorage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateEncorage(@PathVariable Long id, @RequestBody EncorageDTO encorageDTO) {
        String response = encorageService.updateEncorage(id, encorageDTO);
        if (response.equals("Encorage updated successfully")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEncorage(@PathVariable Long id) {
        String response = encorageService.deleteEncorage(id);
        if (response.equals("Encorage deleted successfully")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
