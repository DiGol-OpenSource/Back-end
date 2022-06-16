package com.acme.digol.digol.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateReservationResource {

    private Long id;
    @NotNull
    private String customer;
    private String date;
    private String hour;
    private float prepayment;
}
