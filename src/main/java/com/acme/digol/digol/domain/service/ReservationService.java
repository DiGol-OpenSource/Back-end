package com.acme.digol.digol.domain.service;

import com.acme.digol.digol.domain.model.entity.Customer;
import com.acme.digol.digol.domain.model.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReservationService {
    List<Reservation> getAll();
    Page<Reservation> getAll(Pageable pageable);
    Reservation getById(Long reservationId);
    Reservation create(Reservation customer);
    Reservation update(Long reservationId, Reservation request);
    ResponseEntity<?> delete(Long reservationId);
}
