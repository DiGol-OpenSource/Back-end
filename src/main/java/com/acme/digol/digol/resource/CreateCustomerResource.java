package com.acme.digol.digol.resource;
import lombok.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerResource {

    @NotNull
    private String name;
    private String phoneNumber;
    private String dni;
}
