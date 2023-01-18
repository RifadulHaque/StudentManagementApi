package com.springproject.StudentManagementApi.Entity;

import lombok.Data;

import java.util.Date;

//This is the first step for custom exception,
//add the ErrorObject Entity, with the desired attributes

@Data//getters and setters
public class ErrorObject {

    private Integer statusCode;
    private String message;
    private Date timeStamp;
}
