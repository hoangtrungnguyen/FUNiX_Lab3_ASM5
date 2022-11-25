package studentmanager;

import datalayer.MockStudentDAO;
import org.example.studentmanager.model.Gender;
import org.example.studentmanager.model.Student;
import org.example.studentmanager.model.StudentLevel;
import org.junit.jupiter.params.provider.Arguments;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;



public class Helper {

    static String[] names = new String[]{
            "Trần Quốc Toản",
            "Lê Thánh Tông",
            "Trần Hưng Đạo",
            "Nguyễn Thái Học",
            "Hoàng Trung Nguyên",
            "Võ Nguyên Giáp",
            "Lê Thái Tổ",
            "Nguyễn Thái Học"
    };

    static void setUpStudentData(MockStudentDAO studentDAOTest){
        Student student0 =
                new Student("1", "John",
                        LocalDate.of(2001, 8,22),
                        Gender.MALE, 2.0, StudentLevel.GIOI);
        Student student =
                new Student("2", "Bason",
                        LocalDate.of(2001, 8,22),
                        Gender.MALE, 2.0, StudentLevel.GIOI);

        ArrayList<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student0);
        for(int i = 0; i < 10; i ++){
            Student student1 = new Student(String.valueOf(i),
                    names[new Random().nextInt(names.length ) ],
                    LocalDate.of(2001, 8,22),
                    Gender.MALE, 3.5, StudentLevel.GIOI);
            students.add(student1);
        }

        studentDAOTest.students = students;
    }

    static Stream<Arguments> provideInvalidStudentsObject(){

        Student student1 = new Student(null, "John", LocalDate.of(2000, 10,18), Gender.MALE, 2.0, StudentLevel.GIOI);
        Student student2 = new Student("1", null, LocalDate.of(2000, 10,18), Gender.MALE, 2.0, StudentLevel.GIOI);
        Student student3 = new Student("1", "John", null, Gender.MALE, 2.0, StudentLevel.GIOI);

        Student student4 = new Student("1", "John", LocalDate.of(2000, 10,18), Gender.MALE, null, StudentLevel.GIOI);
        Student student5 = new Student("1", "John", LocalDate.of(2000, 10,18), Gender.MALE, 11.0, StudentLevel.GIOI);

        Student student6 = new Student("1", "John", LocalDate.of(2000, 10,18), Gender.MALE, -1.0, StudentLevel.GIOI);
        Student student7 = new Student("1", "John", LocalDate.of(2000, 10,18), Gender.MALE, -1.0, null);


        return Stream.of(
                Arguments.of(student1, false, "Id can't be null"),
                Arguments.of(student2, false, "Name can't be null"),
                Arguments.of(student3, false, "Date of birth can't be null"),
                Arguments.of(student4, false, "GPA can't be null"),
                Arguments.of(student5, false, "GPA can't be larger than 10.0"),
                Arguments.of(student6, false, "GPA can't be smaller than 0.0"),
                Arguments.of(student7, false, "Student level can't be null")
        );
    }


    static Stream<Arguments> provideStudentObject_ForFunctionCheckIfStudentIsValid(){
        Student student = new Student("1", "John", LocalDate.of(2000, 10,18), Gender.MALE, 2.0, StudentLevel.GIOI);

        Student student1 = new Student(null, "John", LocalDate.of(2000, 10,18), Gender.MALE, 2.0, StudentLevel.GIOI);
        Student student2 = new Student("1", null, LocalDate.of(2000, 10,18), Gender.MALE, 2.0, StudentLevel.GIOI);
        Student student3 = new Student("1", "John", null, Gender.MALE, 2.0, StudentLevel.GIOI);

        Student student4 = new Student("1", "John", LocalDate.of(2000, 10,18), Gender.MALE, null, StudentLevel.GIOI);
        Student student5 = new Student("1", "John", LocalDate.of(2000, 10,18), Gender.MALE, 11.0, StudentLevel.GIOI);

        Student student6 = new Student("1", "John", LocalDate.of(2000, 10,18), Gender.MALE, -1.0, StudentLevel.GIOI);
        Student student7 = new Student("1", "John", LocalDate.of(2000, 10,18), Gender.MALE, -1.0, null);


        return Stream.of(
                Arguments.of(student, true, "Ok"),
                Arguments.of(student1, false, "Id can't be null"),
                Arguments.of(student2, false, "Name can't be null"),
                Arguments.of(student3, false, "Date of birth can't be null"),
                Arguments.of(student4, false, "GPA can't be null"),
                Arguments.of(student5, false, "GPA can't be larger than 10.0"),
                Arguments.of(student6, false, "GPA can't be smaller than 0.0"),
                Arguments.of(student7, false, "Student level can't be null")
        );
    }


    static Stream<Arguments> provideDataFor_CreateStudent_Success(){

        Student s1 = new Student("suenaf-asswer-dfffjj", "John", LocalDate.of(2000, 10,18), Gender.MALE, 2.0, StudentLevel.GIOI);
        Student s2 = new Student("suenaf-asswe", "", LocalDate.of(2003, 10,18), Gender.MALE, 1.4, StudentLevel.TRUNG_BINH);
        Student s3 = new Student("suenaf", "John", LocalDate.of(2000, 10,18), Gender.MALE, 2.0, StudentLevel.GIOI);

        return Stream.of(
                Arguments.of(s1,"Create success"),
                Arguments.of(s2,"Create success"),
                Arguments.of(s3,"Create success")
        );
    }



        static Stream<Arguments> provideDataFor_UpdateUser_Success(){
        Student student = new Student("1", "John", LocalDate.of(2000, 10,18), Gender.MALE, 2.0, StudentLevel.GIOI);
            return Stream.of(
                Arguments.of(student, "Success case"),
                Arguments.of(student, "Ca")
        );
    }
}
