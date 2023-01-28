package com.springproject.StudentManagementApi.Service;

import com.springproject.StudentManagementApi.Entity.User;
import com.springproject.StudentManagementApi.Entity.UserModel;

public interface UserService {

    User createUser(UserModel user);

    User readUser(Long id);

    User update (UserModel userModel, Long id);

    void deleteUser(Long id);

}
