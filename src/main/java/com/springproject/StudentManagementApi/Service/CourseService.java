package com.springproject.StudentManagementApi.Service;

import com.springproject.StudentManagementApi.Entity.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourses();

    Course getCourseById(Long id);

    void deleteCourseById(Long id);

    Course saveCourseDetails(Course course);
}
