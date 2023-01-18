package com.springproject.StudentManagementApi.Service;

import com.springproject.StudentManagementApi.Entity.Course;
import com.springproject.StudentManagementApi.exceptions.ResourceNotFoundException;
import com.springproject.StudentManagementApi.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepo;

    //added Pageable to it as it returns many items, hence limiting the display items
    @Override
    public Page<Course> getAllCourses(Pageable page) {
        return courseRepo.findAll(page);
    }

    @Override
    public Course getCourseById(Long id) {
        Optional<Course> course = courseRepo.findById(id);
        //check if the course object is present or not
        if(course.isPresent()){
            return course.get();
        }
        throw new ResourceNotFoundException("Course is not found for the id " + id);
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

    @Override
    public Course updateCourseDetails(Long id, Course course) {

        //store the current details inside an object and then keep comparing the object
        Course existingCourse = getCourseById(id);

        //checks if the object that is passed contains the changes or not, if not then use the existing one
        existingCourse.setCourse_code(course.getCourse_code() != null ? course.getCourse_code() : existingCourse.getCourse_code());
        existingCourse.setCourse_name(course.getCourse_name() != null ? course.getCourse_name() : existingCourse.getCourse_name());
        existingCourse.setCourse_professor(course.getCourse_professor() != null ? course.getCourse_professor() : existingCourse.getCourse_professor());
        existingCourse.setCourse_status(course.getCourse_status() != null ? course.getCourse_status() : existingCourse.getCourse_status());
        existingCourse.setSemester(course.getSemester() != null? course.getSemester() : existingCourse.getSemester());

        //save the changes on the object in which all the details are called at first
        return courseRepo.save(existingCourse);
    }
}
