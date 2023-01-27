package com.springproject.StudentManagementApi.Entity;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserModel {

    @NotBlank(message = "Email has to be entered")
    @Email(message = "Enter a valid email")
    @Column(unique = true)
    private String email;

    @NotNull(message = "password can not be null")
    @Size(min = 4, message = "password should be at least 4 characters")
    private String password;

    @NotBlank(message = "name can not be blank")
    private String name;

    private Long age = 0L;

}
