package com.service;

import java.util.List;

import com.domain.Encorage;
import com.dto.EncorageDTO;

public interface EncorageService {
    String addEncorage(EncorageDTO encorageDTO);
    List<Encorage> getAllEncorage();
    Encorage getEncorageById(Long id);
    String updateEncorage(Long id, EncorageDTO encorageDTO);
    String deleteEncorage(Long id);
}
