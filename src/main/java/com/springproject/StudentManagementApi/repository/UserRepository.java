package com.springproject.StudentManagementApi.repository;

import com.springproject.StudentManagementApi.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //key word is existsBy
    Boolean existsByEmail(String email);

    //key word is findBy and Email also exists in User Entity class
    Optional<User> findByEmail(String email);

}
