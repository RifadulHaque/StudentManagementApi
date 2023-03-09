package com.springproject.StudentManagementApi.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data//getters and setters
@NoArgsConstructor
@AllArgsConstructor
@Entity//for mysql we use entity
@Table(name = "tbl_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String email;

    //JsonIgnore helps to hide the password from being displayed
    @JsonIgnore//this is used to that the password is not returned to the user, as we want it to remain private
    private String password;

    private String name;

    private Long age;

}
