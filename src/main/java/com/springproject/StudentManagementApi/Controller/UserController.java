package com.springproject.StudentManagementApi.Controller;

import com.springproject.StudentManagementApi.Entity.User;
import com.springproject.StudentManagementApi.Entity.UserModel;
import com.springproject.StudentManagementApi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //Response Entity is used to reply with the HTTP status
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserModel userModel){
        return new ResponseEntity<User>(userService.createUser(userModel), HttpStatus.CREATED);
    }



}
