package com.springproject.StudentManagementApi.Service;

import com.springproject.StudentManagementApi.Entity.User;
import com.springproject.StudentManagementApi.Entity.UserModel;
import com.springproject.StudentManagementApi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(UserModel userModel) {

        User newUser = new User();
        //source(userModel) -> target(newUser)
        //copy from userModel to newUser
        BeanUtils.copyProperties(userModel, newUser);
        //save the properties from newUser
        return userRepository.save(newUser);
    }
}
