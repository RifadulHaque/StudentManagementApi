package com.springproject.StudentManagementApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//API is an Application programming interface which allows two or more services to communicate among each other
//It is a collection of URL and each URL contains an end point

//architecture of the Rest API
//controller <--> service <--> repository(database layer)

@SpringBootApplication
public class StudentManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApiApplication.class, args);
	}

}
