package com.acme.digol.digol.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResource {
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
