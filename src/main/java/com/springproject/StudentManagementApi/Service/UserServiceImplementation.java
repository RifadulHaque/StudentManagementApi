package com.springproject.StudentManagementApi.Service;

import com.springproject.StudentManagementApi.Entity.User;
import com.springproject.StudentManagementApi.Entity.UserModel;
import com.springproject.StudentManagementApi.exceptions.ItemAlreadyExistsException;
import com.springproject.StudentManagementApi.exceptions.ResourceNotFoundException;
import com.springproject.StudentManagementApi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

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
        //encrypt the password before saving it to the database
        //this will convert the plain password to encrypted password to save it to the database
        newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
        //save the properties from newUser
        return userRepository.save(newUser);
    }

    @Override
    public User readUser() throws ResourceNotFoundException {
//        Optional<User> user = userRepository.findById(id);
//
//        if(user.isPresent()) {
//            return user.get();
//        }
//        throw new ResourceNotFoundException("User does not exist with id" + id);
        Long userId = getLoggedInUser().getId();

        return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User does not exist with id"+ userId));
    }

    @Override
    public User update(UserModel userModel) {

        User existingUser = readUser();
        //checks if the object that is passed contains the changes or not, if not then use the existing one
        existingUser.setEmail(userModel.getEmail() != null ? userModel.getEmail() : existingUser.getEmail());
        existingUser.setName(userModel.getName() != null ? userModel.getName() : existingUser.getName());
        //existingUser.setPassword(userModel.getPassword() != null ? userModel.getPassword() : existingUser.getPassword());
        existingUser.setPassword(userModel.getPassword() != null ? bcryptEncoder.encode(userModel.getPassword()) : existingUser.getPassword());
        existingUser.setAge(userModel.getAge() != null ? userModel.getAge() : existingUser.getAge());

        return userRepository.save(existingUser);
    }

    //fist store the user in an object by getting it through the id and then delete the object
    @Override
    public void deleteUser() {
        User existingUser = readUser();
        userRepository.delete(existingUser);
    }

    @Override
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found for the email :" + email));
    }
}
