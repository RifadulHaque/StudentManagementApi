package com.springproject.StudentManagementApi.Controller;

import com.springproject.StudentManagementApi.Entity.User;
import com.springproject.StudentManagementApi.Entity.UserModel;
import com.springproject.StudentManagementApi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//added the user security on the pom.xml file and then entered the {{url}}/login -> first password is available on the console
// In postman, user authentication needs to added in the authentication tab, default credentials are user and pass -> generated in the terminal
//

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //Response Entity is used to reply with the HTTP status
    //@Valid is added to check if all the validation such as notblank or notnull condition is met for userModel
//    @PostMapping("/register")
//    public ResponseEntity<User> registerUser(@Valid @RequestBody UserModel userModel){
//        return new ResponseEntity<User>(userService.createUser(userModel), HttpStatus.CREATED);
//    }

    @GetMapping("/profile")
    public ResponseEntity<User> registerUser() {
        return new ResponseEntity<User>(userService.readUser(), HttpStatus.OK);
    }

    //used for updating the details
    @PutMapping("/profile")
    public ResponseEntity<User> updateUserDetails(@RequestBody UserModel userModel) {
        return new ResponseEntity<User>(userService.update(userModel), HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/deactivate")
    public ResponseEntity<HttpStatus> deleteUser() {
        userService.deleteUser();
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

}
