package com.acme.digol.digol.domain.persistence;
import com.acme.digol.digol.domain.model.entity.Reservation;
import com.acme.digol.digol.domain.model.entity.SportField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findBySportFieldId(Long sportFieldId);

    Page<Reservation> findBySportFieldId(Long sportFieldId, Pageable pageable);

    Optional<Reservation> findByIdAndSportFieldId(Long id, Long sportFieldId);
}
