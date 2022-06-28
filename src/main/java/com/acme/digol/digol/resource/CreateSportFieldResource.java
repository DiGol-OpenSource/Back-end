package com.acme.digol.digol.resource;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateSportFieldResource {

    @NotBlank
    private String name;
    private String img;
    private String address;
    private String description;
    private float price;
    private String sport;

}
