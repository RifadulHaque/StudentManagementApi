package com.springproject.StudentManagementApi.repository;

import com.springproject.StudentManagementApi.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//setting the JPA repository interface for accessing all the in build methods it has to offer, setting the name of the
//entity class and the primary key type in it
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {


}
