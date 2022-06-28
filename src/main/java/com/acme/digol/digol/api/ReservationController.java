package com.acme.digol.digol.api;
import com.acme.digol.digol.domain.service.ReservationService;
import com.acme.digol.digol.mapping.ReservationMapper;
import com.acme.digol.digol.resource.ReservationResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Reservations")
@RestController
@RequestMapping("api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    private final ReservationMapper mapper;


    public ReservationController(ReservationService reservationService, ReservationMapper mapper) {
        this.reservationService = reservationService;
        this.mapper = mapper;
    }
    @GetMapping
    public Page<ReservationResource> getAllReservations(Pageable pageable){
    return mapper.modelListPage(reservationService.getAll(),pageable);
    }
}
