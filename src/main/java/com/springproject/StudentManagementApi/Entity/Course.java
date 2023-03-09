package com.springproject.StudentManagementApi.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//add this in the properties
//With the statement below, this will automatically create the database table using the entity class configuration
//spring.jpa.hibernate.ddl-auto=update
//spring.jpa.show-sql=true // this will configure the sql statements in the console



@Data//getters and setters
@NoArgsConstructor
@AllArgsConstructor
@Entity//for mysql database we use Entity annotation
@Table(name = "enrolled_courses")//the name of the entity table from mysql database
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //added validations to the fields
    @NotBlank(message = "Course Name must not be null")
    @Size(min = 5, message = "Course Name must be at least 5 characters")
    private String course_name;

    @NotNull(message = "Course Code must not be null")
    @Column(name = "course_code")
    private String code;

    @Column(name= "course_status")
    private String status;

    @NotBlank(message = "Course Name must not be null")
    @Column(name= "course_professor")
    private String professor;


    @Column(name = "enrolled_semester")
    private String semester;

    //it is uni-directional as in the user class, one to many is not mentioned
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)//adding a new column for each course
    @OnDelete(action = OnDeleteAction.CASCADE)//On deletion of the user, the course needs to be deleted also
    @JsonIgnore//when we fecth the course, we will not fetch the user
    private User user;

}
