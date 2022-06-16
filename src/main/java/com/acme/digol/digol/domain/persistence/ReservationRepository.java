package com.acme.digol.digol.domain.persistence;
import com.acme.digol.digol.domain.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByCustomer(String customer);
    Reservation findByCustomer(String name);
}
