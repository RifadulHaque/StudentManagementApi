package com.springproject.StudentManagementApi.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private String course_name;

    private String course_code;

    private String course_status;

    private String course_professor;

    @Column(name = "enrolled_semester")
    private String semester;

}
