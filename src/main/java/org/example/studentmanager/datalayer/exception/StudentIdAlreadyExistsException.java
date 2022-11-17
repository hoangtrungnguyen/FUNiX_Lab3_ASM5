package org.example.studentmanager.datalayer.exception;

import java.sql.SQLDataException;

public class StudentIdAlreadyExistsException extends SQLDataException {

    StudentIdAlreadyExistsException(){
        super("Student id is already exist. Please create other student.");
    }
}
