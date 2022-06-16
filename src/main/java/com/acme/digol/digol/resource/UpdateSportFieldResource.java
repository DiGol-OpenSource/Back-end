package com.acme.digol.digol.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateSportFieldResource {
    private Long id;
    @NotNull
    private String name;
    private String img;
    private String address;
    private String description;
    private float price;
}
