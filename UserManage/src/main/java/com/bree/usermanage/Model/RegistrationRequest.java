package com.bree.usermanage.Model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@EqualsAndHashCode
public class RegistrationRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String Password;



}
