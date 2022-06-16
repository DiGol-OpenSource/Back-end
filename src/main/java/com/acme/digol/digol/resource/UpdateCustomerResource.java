package com.acme.digol.digol.resource;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter

public class UpdateCustomerResource {

    private Long id;
    @NotNull
    private String name;
    private String phoneNumber;
    private String dni;
}
