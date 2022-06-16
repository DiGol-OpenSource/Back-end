package com.acme.digol.digol.api;
import com.acme.digol.digol.domain.service.ReservationService;
import com.acme.digol.digol.mapping.ReservationMapper;
import com.acme.digol.digol.resource.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

    @Operation(summary = "Get reservations", description = "Get All reservations.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SportFieldResource.class))})
    })
    @GetMapping
    public Page<ReservationResource> getAllReservations(Pageable pageable) {
        return mapper.modelListPage(reservationService.getAll(), pageable);
    }
    @Operation(summary = "Get reservations with id ", description = "Get reservations with id.")
    @GetMapping("{reservationId}")
    public ReservationResource getReservationId(@PathVariable Long reservationId) {
        return mapper.toResource(reservationService.getById(reservationId));
    }
    @Operation(summary = "Post reservations", description = "Post reservations.")
    @PostMapping
    public ReservationResource createReservation(@RequestBody CreateReservationResource resource) {
        return mapper.toResource(reservationService.create(mapper.toModel(resource)));
    }
    @Operation(summary = "Edit reservations", description = "Edit reservations")
    @PutMapping("{reservationId}")
    public ReservationResource updateReservation(@PathVariable Long reservationId, @RequestBody UpdateReservationResource resource) {
        return mapper.toResource(reservationService.update(reservationId, mapper.toModel(resource)));
    }
    @Operation(summary = "Delete reservations", description = "Delete reservations.")
    @DeleteMapping("{reservationId}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long reservationId) {
        return reservationService.delete(reservationId);
    }
}
