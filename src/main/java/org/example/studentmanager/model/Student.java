package org.example.studentmanager.model;

import com.sun.tools.javac.jvm.Gen;

import java.time.LocalDate;
import java.util.Date;

public class Student {
    private String id;
    private String name;
    private LocalDate DOB;
    private Gender gender;
    private Double GPA;
    private StudentLevel studentLevel;

    public Student(String id, String name, LocalDate DOB, Gender gender, Double GPA, StudentLevel studentLevel) {
        this.id = id;
        this.name = name;
        this.DOB = DOB;
        this.gender = gender;
        this.GPA = GPA;
        this.studentLevel = studentLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Double getGPA() {
        return GPA;
    }

    public void setGPA(Double GPA) {
        this.GPA = GPA;
    }

    public StudentLevel getLevel() {
        return studentLevel;
    }

    public void setLevel(StudentLevel level) {
        this.studentLevel = level;
    }

    public StudentLevel getStudentLevel() {
        return studentLevel;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", DOB=" + DOB +
                ", gender='" + gender + '\'' +
                ", GPA=" + GPA +
                ", studentLevel=" + studentLevel +
                '}';
    }
}

