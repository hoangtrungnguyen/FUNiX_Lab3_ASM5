package org.example.studentmanager.datalayer.exception;

import org.example.studentmanager.model.Student;

public class StudentNotFoundException extends  Exception{
    public  StudentNotFoundException(Student student){
        super("Can't find student with id: "+ student.getId());
    }
}
