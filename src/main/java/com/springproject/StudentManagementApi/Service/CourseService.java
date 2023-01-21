package com.springproject.StudentManagementApi.Service;

import com.springproject.StudentManagementApi.Entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {

    //added Pageable to it as it returns many items, hence limiting the display items
    Page<Course> getAllCourses(Pageable page);

    Course getCourseById(Long id);

    void deleteCourseById(Long id);

    Course saveCourseDetails(Course course);

    Course updateCourseDetails(Long id, Course course);

    List<Course> readByCourseCode(String code, Pageable page);
    List<Course> readByCourseStatus(String status, Pageable page);

    List<Course> readByCourseCodeContaining(String keyword, Pageable page);
}
