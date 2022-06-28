package com.acme.digol.digol.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdateUserResource {
    private Long id;
    @NotBlank
    private String name;
    private String email;
    private String type;
    private String password;
    private String password2;
    private String location;
    private String cellPhone;
    private String profile;
}
