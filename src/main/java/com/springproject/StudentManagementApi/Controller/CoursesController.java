package com.springproject.StudentManagementApi.Controller;

import com.springproject.StudentManagementApi.Entity.Course;
import com.springproject.StudentManagementApi.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

//There are two ways to pass parameters in url, 1. using path variables  @pathvariable("id") 2. using string query @RequestParam("id)

@RestController
public class CoursesController {

    @Autowired
    private CourseService courseService;

    //Added Pageable which allows the data to be sorted in page with size of items in it.
    //converted it to a list for better display
    @GetMapping("/courses")
    public List<Course> getAllCourses(Pageable page) {
        return courseService.getAllCourses(page).toList();
    }

    //using string query
    @GetMapping("/courses/{id}")
    public Course getCourseById(@PathVariable("id") Long id) {

        return courseService.getCourseById(id);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/courses")
    public void deleteCourseById(@RequestParam("id") Long id) {

        courseService.deleteCourseById(id);
    }

    //@RequestBody maps the JSON HTTP details to JAVA objects
    //@Valid will check if the save request is NotNull or NotBlank
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/courses")
    public Course saveCourseDetails(@Valid @RequestBody Course course) {
        return courseService.saveCourseDetails(course);
    }

    @PutMapping("/courses")
    public Course updateCourseDetails(@RequestBody Course course, @RequestParam("id") Long id) {
        return courseService.updateCourseDetails(id, course);
    }

}
