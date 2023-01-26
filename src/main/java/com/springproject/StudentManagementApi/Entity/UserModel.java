package com.springproject.StudentManagementApi.Entity;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserModel {

    @Column(unique = true)
    private String email;

    private String password;

    private String name;

    private Long age = 0L;

}
