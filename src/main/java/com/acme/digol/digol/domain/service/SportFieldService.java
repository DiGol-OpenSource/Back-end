package com.acme.digol.digol.domain.service;

import com.acme.digol.digol.domain.model.entity.SportField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SportFieldService {
    List<SportField> getAll();
    Page<SportField> getAll(Pageable pageable);
    SportField getById(Long sportFieldId);
    SportField create(SportField sportField);
    SportField update(Long sportFieldId, SportField request);
    ResponseEntity<?> delete(Long studentId);
}
