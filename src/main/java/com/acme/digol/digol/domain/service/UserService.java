package com.acme.digol.digol.domain.service;

import com.acme.digol.digol.domain.model.entity.SportField;
import com.acme.digol.digol.domain.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<User> getAll();
    Page<User> getAll(Pageable pageable);
    User getById(Long userId);
    User create(User user);
    User update(Long userId, User request);
    ResponseEntity<?> delete(Long studentId);
}
