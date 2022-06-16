package com.acme.digol.digol.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class SportFieldResource {
    private Long id;
    private String name;
    private String img;
    private String address;
    private String description;
    private float price;

}
