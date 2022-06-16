package com.acme.digol.digol.resource;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationResource {
    @NotNull
    private String customer;
    private String date;
    private String hour;
    private float prepayment;
}
