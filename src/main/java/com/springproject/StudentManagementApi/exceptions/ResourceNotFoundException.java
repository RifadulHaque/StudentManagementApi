package com.springproject.StudentManagementApi.exceptions;

//This is the second step for the custom Exception Handler
//This extends the RuntimeException
//This class calls the constructor of the parent class using super()

public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID=  1L;

    public ResourceNotFoundException(String message) {
        super(message);

    }
}
