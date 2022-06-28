package com.acme.digol.digol.api;

import com.acme.digol.digol.domain.model.entity.Reservation;
import com.acme.digol.digol.domain.service.ReservationService;
import com.acme.digol.digol.mapping.ReservationMapper;
import com.acme.digol.digol.resource.ReservationResource;
import com.acme.digol.digol.resource.UpdateReservationResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "SportFields-Reservations")
@RestController
@RequestMapping("/api/v1/sportFields/{sportFieldId}/reservations")
public class SportFieldReservationController {
private final ReservationService reservationService;
private final ReservationMapper mapper;

    public SportFieldReservationController(ReservationService reservationService, ReservationMapper mapper) {
        this.reservationService = reservationService;
        this.mapper = mapper;
    }
@GetMapping
public Page<ReservationResource> getAllReservationsBySportFieldId(@PathVariable Long sportFieldId, Pageable pageable){
return mapper.modelListPage(reservationService.getAllBySportFieldId(sportFieldId),pageable);
}

@PostMapping
public ReservationResource createReservation(@PathVariable Long sportFieldId,
                                             @RequestBody UpdateReservationResource resource){
return mapper.toResource(reservationService.create(sportFieldId,mapper.toModel(resource)));
}

@PutMapping("{reservationId}")
public ReservationResource updateReservation(@PathVariable Long sportFieldId,
                                             @PathVariable Long reservationId,
                                             @RequestBody UpdateReservationResource resource){
        return mapper.toResource(reservationService.update(sportFieldId,reservationId,mapper.toModel(resource)));

}
@DeleteMapping("{reservationId}")
public ResponseEntity<?> deleteReservation(@PathVariable Long sportFieldId,
                                           @PathVariable Long reservationId){
return reservationService.delete(sportFieldId, reservationId);
}
}
