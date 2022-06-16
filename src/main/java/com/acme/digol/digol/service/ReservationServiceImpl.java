package com.acme.digol.digol.service;

import com.acme.digol.digol.domain.model.entity.Reservation;
import com.acme.digol.digol.domain.persistence.ReservationRepository;
import com.acme.digol.digol.domain.service.ReservationService;
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
public class ReservationServiceImpl implements ReservationService {
    private static final String ENTITY ="Reservation";
    private final ReservationRepository reservationRepository;
    private final Validator validator;

    public ReservationServiceImpl(ReservationRepository reservationRepository, Validator validator){
        this.reservationRepository=reservationRepository;
        this.validator=validator;
    }
    @Override
    public List<Reservation> getAll(){return reservationRepository.findAll();}

    @Override
    public Page<Reservation> getAll(Pageable pageable) {
        return reservationRepository.findAll(pageable);
    }
    @Override
    public Reservation getById(Long reservationId) {
        return reservationRepository.findById(reservationId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, reservationId));
    }

    @Override
    public Reservation create(Reservation reservation) {

        Set<ConstraintViolation<Reservation>> violations = validator.validate(reservation);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        // Name Uniqueness validation

        Reservation reservationWithName = reservationRepository.findByCustomer(reservation.getCustomer());

        if (reservationWithName != null)
            throw new ResourceValidationException(ENTITY,
                    "An reservation with the same name already exists.");

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation update(Long reservationId, Reservation request) {
        Set<ConstraintViolation<Reservation>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        // Name Uniqueness validation

        Reservation reservationWithName = reservationRepository.findByCustomer(request.getCustomer());

        if (reservationWithName != null && !reservationWithName.getId().equals(reservationId))
            throw new ResourceValidationException(ENTITY,
                    "An reservation with the same name already exists.");

        return reservationRepository.findById(reservationId).map(student ->
                        reservationRepository.save(student.withCustomer(request.getCustomer())
                                .withDate(request.getDate())
                                .withHour(request.getHour())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, reservationId));
    }

    @Override
    public ResponseEntity<?> delete(Long reservationId) {
        return reservationRepository.findById(reservationId).map(student -> {
            reservationRepository.delete(student);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, reservationId));
    }
}
