package com.springproject.StudentManagementApi.repository;

import com.springproject.StudentManagementApi.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //key word is exists
    Boolean existsByEmail(String email);

}
