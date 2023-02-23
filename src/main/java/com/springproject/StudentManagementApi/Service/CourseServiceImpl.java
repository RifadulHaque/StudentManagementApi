package com.springproject.StudentManagementApi.Service;

import com.springproject.StudentManagementApi.Entity.Course;
import com.springproject.StudentManagementApi.exceptions.ResourceNotFoundException;
import com.springproject.StudentManagementApi.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private UserService userService;

    //added Pageable to it as it returns many items, hence limiting the display items
    //we call the repository user which helps to get the logged in user id for finding all the courses by targeting  each user
    @Override
    public Page<Course> getAllCourses(Pageable page) {
        return courseRepo.findByUserId(userService.getLoggedInUser().getId(), page);
    }

    @Override
    public Course getCourseById(Long id) {
        Optional<Course> course = courseRepo.findByUserIdAndId(userService.getLoggedInUser().getId(), id);
        //check if the course object is present or not
        if(course.isPresent()){
            return course.get();
        }
        throw new ResourceNotFoundException("Course is not found for the id " + id);
    }

    @Override
    public void deleteCourseById(Long id) {
        Course course = getCourseById(id);
        //courseRepo.deleteById(course);
        courseRepo.delete(course);
    }

    //passing the object of course in the save method which in tern will save the details in the database
    @Override
    public Course saveCourseDetails(Course course) {

        course.setUser(userService.getLoggedInUser());
        return courseRepo.save(course);

    }

    @Override
    public Course updateCourseDetails(Long id, Course course) {

        //store the current details inside an object and then keep comparing the object
        Course existingCourse = getCourseById(id);

        //checks if the object that is passed contains the changes or not, if not then use the existing one
        existingCourse.setCode(course.getCode() != null ? course.getCode() : existingCourse.getCode());
        existingCourse.setCourse_name(course.getCourse_name() != null ? course.getCourse_name() : existingCourse.getCourse_name());
        existingCourse.setProfessor(course.getProfessor() != null ? course.getProfessor() : existingCourse.getProfessor());
        existingCourse.setStatus(course.getStatus() != null ? course.getStatus() : existingCourse.getStatus());
        existingCourse.setSemester(course.getSemester() != null? course.getSemester() : existingCourse.getSemester());

        //save the changes on the object in which all the details are called at first
        return courseRepo.save(existingCourse);
    }

    @Override
    public List<Course> readByCourseCode(String code, Pageable page) {
        return courseRepo.findByUserIdAndCode(userService.getLoggedInUser().getId(), code, page).toList(); //converted toList as its initial return type was Pageable
    }

    @Override
    public List<Course> readByCourseStatus(String status, Pageable page) {
        return courseRepo.findByUserIdAndStatus(userService.getLoggedInUser().getId(), status, page).toList(); //converted toList as its initial return type was Pageable
    }

    @Override
    public List<Course> readByCourseCodeContaining(String keyword, Pageable page) {
        return courseRepo.findByUserIdAndCodeContaining(userService.getLoggedInUser().getId(), keyword, page).toList();
    }
}
