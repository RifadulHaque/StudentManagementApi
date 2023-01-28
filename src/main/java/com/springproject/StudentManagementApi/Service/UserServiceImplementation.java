package com.springproject.StudentManagementApi.Service;

import com.springproject.StudentManagementApi.Entity.User;
import com.springproject.StudentManagementApi.Entity.UserModel;
import com.springproject.StudentManagementApi.exceptions.ItemAlreadyExistsException;
import com.springproject.StudentManagementApi.exceptions.ResourceNotFoundException;
import com.springproject.StudentManagementApi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(UserModel userModel) {

        //check for existance
        if(userRepository.existsByEmail(userModel.getEmail())) {
            throw new ItemAlreadyExistsException("User already exists"+userModel.getEmail());
        }

        User newUser = new User();
        //source(userModel) -> target(newUser)
        //copy from userModel to newUser
        BeanUtils.copyProperties(userModel, newUser);
        //save the properties from newUser
        return userRepository.save(newUser);
    }

    @Override
    public User readUser(Long id) throws ResourceNotFoundException {
//        Optional<User> user = userRepository.findById(id);
//
//        if(user.isPresent()) {
//            return user.get();
//        }
//        throw new ResourceNotFoundException("User does not exist with id" + id);

        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User does not exist with id"+ id));
    }

    @Override
    public User update(UserModel userModel, Long id) {

        User existingUser = readUser(id);
        //checks if the object that is passed contains the changes or not, if not then use the existing one
        existingUser.setEmail(userModel.getEmail() != null ? userModel.getEmail() : existingUser.getEmail());
        existingUser.setName(userModel.getName() != null ? userModel.getName() : existingUser.getName());
        existingUser.setPassword(userModel.getPassword() != null ? userModel.getPassword() : existingUser.getPassword());
        existingUser.setAge(userModel.getAge() != null ? userModel.getAge() : existingUser.getAge());

        return userRepository.save(existingUser);
    }

    //fist store the user in an object by getting it through the id and then delete the object
    @Override
    public void deleteUser(Long id) {
        User existingUser = readUser(id);
        userRepository.delete(existingUser);
    }
}
