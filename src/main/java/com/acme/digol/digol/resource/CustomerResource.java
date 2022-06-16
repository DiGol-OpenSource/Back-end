package com.acme.digol.digol.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResource {
    private Long id;
    private String name;
    private String phoneNumber;
    private String dni;

}
