package com.contactmanager.scm.util;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class UserForm {
    @NotBlank(message = "Firstname can not be blank.")
    private String firstName;
    private String lastName;
    @NotBlank(message = "Email can not be blank.")
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank(message = "Password can not be blank.")
    @Size(min = 6, message="Minimun 6 char required.")
    private String password;
    @NotBlank(message = "Phone can not be blank.")
    private String phone;
    private String about;
}
