package com.blogapplication.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Empty;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min = 4,message = "Username must be minimum of 4 characters !!")
    private String name;

    @Email(message = "email address is not valid !!")
    private String email;

    @NotEmpty
    @Size(min=3, max=10, message = "password must be minimum of 3 characters and maximum of 10 characters !!")
    private String password;

    @NotEmpty
    private String about;


}
