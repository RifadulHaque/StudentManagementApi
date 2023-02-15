package com.springproject.StudentManagementApi.Controller;

import com.springproject.StudentManagementApi.Entity.LoginModel;
import com.springproject.StudentManagementApi.Entity.User;
import com.springproject.StudentManagementApi.Entity.UserModel;
import com.springproject.StudentManagementApi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login (@RequestBody LoginModel loginModel) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginModel.getEmail(),loginModel.getPassword()));

        //the security context holder has to be used to get the authentication of the email and password
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    //Response Entity is used to reply with the HTTP status
    //@Valid is added to check if all the validation such as notblank or notnull condition is met for userModel
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserModel userModel){
        return new ResponseEntity<User>(userService.createUser(userModel), HttpStatus.CREATED);
    }

}
