package com.acme.digol.digol.service;

import com.acme.digol.digol.domain.model.entity.SportField;
import com.acme.digol.digol.domain.persistence.UserRepository;
import com.acme.digol.digol.domain.service.UserService;
import org.springframework.stereotype.Service;

import com.acme.digol.digol.domain.model.entity.User;
import com.acme.digol.shared.exception.ResourceNotFoundException;
import com.acme.digol.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private static final String ENTITY ="User";
    private final UserRepository userRepository;
    private final Validator validator;

    public UserServiceImpl(UserRepository userRepository, Validator validator){
        this.userRepository=userRepository;
        this.validator=validator;
    }
    @Override
    public List<User> getAll(){return userRepository.findAll();}

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public User create(User user) {

        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        // Name Uniqueness validation

        User sportFieldWithName = userRepository.findByName(user.getName());

        if (sportFieldWithName != null)
            throw new ResourceValidationException(ENTITY,
                    "An SportField with the same name already exists.");

        return userRepository.save(user);
    }

    @Override
    public User update(Long userId, User request) {
        Set<ConstraintViolation<User>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        // Name Uniqueness validation

        User userWithName = userRepository.findByName(request.getName());

        if (userWithName != null && !userWithName.getId().equals(userId))
            throw new ResourceValidationException(ENTITY,
                    "An SportField with the same name already exists.");

        return userRepository.findById(userId).map(student ->
                        userRepository.save(student.withName(request.getName())
                                .withEmail(request.getEmail())
                                .withType(request.getType())
                                .withCellPhone(request.getCellPhone()))).
                orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public ResponseEntity<?> delete(Long userId) {
        return userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }
}
