package com.springproject.StudentManagementApi.repository;

import com.springproject.StudentManagementApi.Entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;


//setting the JPA repository interface for accessing all the in build methods it has to offer, setting the name of the
//entity class and the primary key type in it
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    //find by a specific category
    //SELECT * FROM tbl_expenses WHERE user_id=? AND code=?
    //it is not an inbuild method of JpaRepository hence I had to add it here
    //the name of findBy should be included in the Entity or else it wont work
    Page<Course> findByUserIdAndCode(Long userId, String code, Pageable page);
    Page<Course> findByUserIdAndStatus(Long userId, String status, Pageable page);

    //containing keyword is used to represent
    //SELECT * FROM tbl_expenses WHERE user_id=? AND name LIKE '%keyword%'
    Page<Course> findByUserIdAndCodeContaining(Long userId, String keyword, Pageable page);
    Page<Course> findByUserId(Long id, Pageable page);

    Optional<Course> findByUserIdAndId(Long userId, Long courseId);
}
