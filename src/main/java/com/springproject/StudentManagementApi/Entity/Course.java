package com.springproject.StudentManagementApi.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//add this in the properties
//With the statement below, this will automatically create the database table using the entity class configuration
//spring.jpa.hibernate.ddl-auto=update
//spring.jpa.show-sql=true // this will configure the sql statements in the console



@Data//getters and setters
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enrolled_courses")//the name of the entity table from mysql database
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Course Name must not be null")
    @Size(min = 5, message = "Course Name must be at least 5 characters")
    private String course_name;

    @NotNull(message = "Course Code must not be null")
    private String course_code;

    private String course_status;

    @NotBlank(message = "Course Name must not be null")
    private String course_professor;

    @Column(name = "enrolled_semester")
    private String semester;

}
