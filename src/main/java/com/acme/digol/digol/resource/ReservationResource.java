package com.acme.digol.digol.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResource {
    private Long id;
    private String customer;
    private String date;
    private String hour;
    private float prepayment;
}
