package com.springproject.StudentManagementApi.Controller;

import com.springproject.StudentManagementApi.Entity.Course;
import com.springproject.StudentManagementApi.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

//There are two ways to pass parameters in url, 1. using path variables  @pathvariable("id") 2. using string query @RequestParam("id)

@RestController
public class CoursesController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    //using string query
    @GetMapping("/courses/{id}")
    public Course getCourseById(@PathVariable("id") Long id) {

        return courseService.getCourseById(id);
    }

    @DeleteMapping("/courses")
    public void deleteCourseById(@RequestParam("id") Long id) {

        courseService.deleteCourseById(id);
    }

    //@RequestBody maps the JSON HTTP details to JAVA objects
    @PostMapping("/courses")
    public Course saveCourseDetails(@RequestBody Course course) {
        return courseService.saveCourseDetails(course);
    }

}
