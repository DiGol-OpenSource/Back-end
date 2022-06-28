package com.acme.digol.digol.domain.service;

import com.acme.digol.digol.domain.model.entity.Customer;
import com.acme.digol.digol.domain.model.entity.Reservation;
import com.acme.digol.digol.domain.model.entity.SportField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReservationService {
    List<Reservation> getAll();

    List<Reservation> getAllBySportFieldId(Long sportFieldId);
    Page<Reservation> getAllBySportFieldId(Long sportFieldId,Pageable pageable);
    Reservation getById(Long reservationId);
    Reservation create(Long sportField , Reservation reservation);
    Reservation update(Long sportFieldId,Long reservationId, Reservation reservation);
    ResponseEntity<?> delete(Long sportFieldId,Long reservationId);
}
