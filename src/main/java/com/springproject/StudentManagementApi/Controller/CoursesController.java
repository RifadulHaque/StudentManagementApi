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


//{{url}}  = localhost:8080/api/v1
/*Login details
   {
    "email": "dev1@gmail.com",
    "password": "dev1"
   }
 */

@RestController
public class CoursesController {

    @Autowired
    private CourseService courseService;

    //Added Pageable which allows the data to be sorted in page with size of items in it.
    //converted it to a list for better display
    //GET {{url}}/courses?page=0&size=2&sort=id,desc
    @GetMapping("/courses")
    public List<Course> getAllCourses(Pageable page) {
        return courseService.getAllCourses(page).toList();
    }

    //this method was first added in CourseRepository and then implemented in CourseServive and then called here
    //GET {{url}}/courses/code?code=ECON-201
    @GetMapping("/courses/code")
    public List<Course> getAllCoursesByCourseCode(@RequestParam String code, Pageable page) {
        return courseService.readByCourseCode(code,page);
    }

    //GET {{url}}/courses/status?status=Completed
    @GetMapping("/courses/status")
    public List<Course> getAllCoursesByCourseStatus(@RequestParam String status, Pageable page) {
        return courseService.readByCourseStatus(status,page);
    }

    // GET {{url}}/courses/codeKeyword?keyword=COEN
    @GetMapping("/courses/codeKeyword")
    public List<Course> getAllCoursesByCourseCodeKeyword(@RequestParam String keyword, Pageable page) {
        return courseService.readByCourseCodeContaining(keyword,page);
    }

    //using string query
    // GET {{url}}/courses/7
    @GetMapping("/courses/{id}")
    public Course getCourseById(@PathVariable("id") Long id) {
        return courseService.getCourseById(id);
    }

    //DELETE {{url}}/courses?id=6
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/courses")
    public void deleteCourseById(@RequestParam("id") Long id) {
        courseService.deleteCourseById(id);
    }

    //@RequestBody maps the JSON HTTP details to JAVA objects
    //@Valid will check if the save request is NotNull or NotBlank
    //added validation to the fields, so that it checks if the details are valid or not
    //POST {{url}}/courses
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/courses")
    public Course saveCourseDetails(@Valid @RequestBody Course course) {
        return courseService.saveCourseDetails(course);
    }

    //used for updating the details
    //PUT {{url}}/courses?id=4
    @PutMapping("/courses")
    public Course updateCourseDetails(@RequestBody Course course, @RequestParam("id") Long id) {
        return courseService.updateCourseDetails(id, course);
    }

}
