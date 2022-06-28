package com.acme.digol.digol.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateReservationResource {

    @NotBlank
    @NotNull
    private String start;
    private String end;
}
