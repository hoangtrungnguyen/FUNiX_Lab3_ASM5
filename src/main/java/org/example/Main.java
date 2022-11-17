package org.example;


import org.example.studentmanager.StudentManager;
import org.example.studentmanager.model.Gender;
import org.example.studentmanager.model.Student;
import org.example.studentmanager.model.StudentLevel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static final String DB_URL = "jdbc:mysql://localhost/";
    static final String USER = "admin";
    static final String PASS = "1810";

    static StudentManager studentManager;

    public static void main(String[] args) {
      chooseFunction();
    }

    public static void chooseFunction ()  {
        System.out.println("Please choose the function: ");
        System.out.println("1. Create Student ");
        System.out.println("3. Update student information ");
        System.out.println("3. List the student ");
        System.out.println("4. Search student ");
        System.out.println("5. Delete student ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Please input student ID");
                String studentId = sc.next();
                sc.nextLine();
                System.out.println("Please input student name");
                String studentName = sc.nextLine();
                System.out.println("Please input student birthday");
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                String studentBirthdayInput = sc.nextLine();
                LocalDate studentBirthday = LocalDate.parse(studentBirthdayInput, dateTimeFormatter);
                System.out.println("Please input student gender, please input true if boy, and false if girl");
                boolean studentGender = sc.nextBoolean();
                System.out.println("Please input student GPA");
                double studentGpa = sc.nextFloat();
                sc.nextLine();
                System.out.println("Please input student student status");
                String studentGrade = sc.nextLine();

                StudentLevel level;

                if(StudentLevel.KHA.name().equalsIgnoreCase(studentGrade)){
                    level = StudentLevel.KHA;
                } else if(StudentLevel.GIOI.name().equalsIgnoreCase(studentGrade)){
                    level = StudentLevel.GIOI;
                } else if(StudentLevel.TRUNG_BINH.name().equalsIgnoreCase(studentGrade)) {
                    level = StudentLevel.TRUNG_BINH;
                } else {
                    level = StudentLevel.TRUNG_BINH;
                }

//                Calendar.getInstance().get
                studentManager.createStudent(
                        new Student(
                                studentId,
                                studentName,
                                studentBirthday,
                                studentGender ? Gender.MALE : Gender.FEMALE,
                                studentGpa,
                                level
                        )
                );
        }
    }
}