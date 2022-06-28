package com.acme.digol.digol.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResource {
    private Long id;
    private String start;
    private String end;
    private String title;
    private SportFieldResource sportField;
}
