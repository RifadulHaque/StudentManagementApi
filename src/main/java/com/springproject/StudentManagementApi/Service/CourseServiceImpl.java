package com.springproject.StudentManagementApi.Service;

import com.springproject.StudentManagementApi.Entity.Course;
import com.springproject.StudentManagementApi.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepo;

    @Override
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        Optional<Course> course = courseRepo.findById(id);
        //check if the course object is present or not
        if(course.isPresent()){
            return course.get();
        }
        throw new RuntimeException("Course is not found for the id" + id);
    }

    @Override
    public void deleteCourseById(Long id) {
        courseRepo.deleteById(id);
    }

    //passing the object of course in the save method which in tern will save the details in the database
    @Override
    public Course saveCourseDetails(Course course) {
        return courseRepo.save(course);
    }
}
