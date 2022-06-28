package com.acme.digol.digol.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationResource {
    @NotBlank
    private String title;
    private String start;
    private String end;
    private SportFieldResource sportField;
}
