package com.acme.digol.digol.service;

import com.acme.digol.digol.domain.model.entity.SportField;
import com.acme.digol.digol.domain.persistence.SportFieldRepository;
import com.acme.digol.digol.domain.service.SportFieldService;
import com.acme.digol.shared.exception.ResourceNotFoundException;
import com.acme.digol.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class SportFieldServiceImpl implements SportFieldService {
    private static final String ENTITY ="SportField";
    private final SportFieldRepository sportFieldRepository;
    private final Validator validator;

    public SportFieldServiceImpl(SportFieldRepository sportFieldRepository, Validator validator){
        this.sportFieldRepository=sportFieldRepository;
        this.validator=validator;
    }
    @Override
    public List<SportField> getAll(){return sportFieldRepository.findAll();}

    @Override
    public Page<SportField> getAll(Pageable pageable) {
        return sportFieldRepository.findAll(pageable);
    }
    @Override
    public SportField getById(Long sportFieldId) {
        return sportFieldRepository.findById(sportFieldId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, sportFieldId));
    }

    @Override
    public SportField create(SportField sportField) {

        Set<ConstraintViolation<SportField>> violations = validator.validate(sportField);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        // Name Uniqueness validation

        SportField sportFieldWithName = sportFieldRepository.findByName(sportField.getName());

        if (sportFieldWithName != null)
            throw new ResourceValidationException(ENTITY,
                    "An SportField with the same name already exists.");

        return sportFieldRepository.save(sportField);
    }

    @Override
    public SportField update(Long sportFieldId, SportField request) {
        Set<ConstraintViolation<SportField>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        // Name Uniqueness validation

        SportField sportFieldWithName = sportFieldRepository.findByName(request.getName());

        if (sportFieldWithName != null && !sportFieldWithName.getId().equals(sportFieldId))
            throw new ResourceValidationException(ENTITY,
                    "An SportField with the same name already exists.");

        return sportFieldRepository.findById(sportFieldId).map(student ->
                        sportFieldRepository.save(student.withName(request.getName())
                                .withDescription(request.getDescription())
                                .withAddress(request.getAddress())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, sportFieldId));
    }

    @Override
    public ResponseEntity<?> delete(Long sportFieldId) {
        return sportFieldRepository.findById(sportFieldId).map(student -> {
            sportFieldRepository.delete(student);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, sportFieldId));
    }
}
