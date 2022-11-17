package studentmanager;

import com.google.gson.Gson;
import datalayer.MockStudentDAO;
import org.example.constant.AppString;
import org.example.studentmanager.model.Student;
import org.example.studentmanager.model.StudentLevel;
import org.junit.jupiter.params.provider.Arguments;

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
        Gson gson = new Gson();
        Student student = new Student(
                "1",
                "John",
                new Date(2000, Calendar.OCTOBER, 18),
                "Male",
                2.0,
                StudentLevel.GIOI
        );

        ArrayList<Student> students = new ArrayList<>();
        students.add(student);
        for(int i = 0; i < 10; i ++){
            Student student1 = gson.fromJson(gson.toJson(student), Student.class);
            student1.setId(String.valueOf(i));
            student1.setName(names[new Random().nextInt(names.length ) ]);
            students.add(student1);
        }

        studentDAOTest.students = students;
    }

    static Stream<Arguments> provideInvalidStudentsObject(){
        Gson gson = new Gson();
        Student student = new Student(
                "1",
                "John",
                new Date(2000, Calendar.OCTOBER, 18),
                "Male",
                2.0,
                StudentLevel.GIOI
        );

        Student student1 = gson.fromJson(gson.toJson(student), Student.class);
        student1.setId(null);

        Student student2 = gson.fromJson(gson.toJson(student), Student.class);
        student2.setName(null);

        Student student3 = gson.fromJson(gson.toJson(student), Student.class);
        student3.setDOB(null);

        Student student4 = gson.fromJson(gson.toJson(student), Student.class);
        student4.setGPA(null);

        Student student5 = gson.fromJson(gson.toJson(student), Student.class);
        student5.setGPA(11.0);

        Student student6 = gson.fromJson(gson.toJson(student), Student.class);
        student6.setGPA(-1.0);


        Student student7 = gson.fromJson(gson.toJson(student), Student.class);
        student7.setLevel(null);

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
        Gson gson = new Gson();
        Student student = new Student(
                "1",
                "John",
                new Date(2000, Calendar.OCTOBER, 18),
                "Male",
                2.0,
                StudentLevel.GIOI
        );

        Student student1 = gson.fromJson(gson.toJson(student), Student.class);
        student1.setId(null);

        Student student2 = gson.fromJson(gson.toJson(student), Student.class);
        student2.setName(null);

        Student student3 = gson.fromJson(gson.toJson(student), Student.class);
        student3.setDOB(null);

        Student student4 = gson.fromJson(gson.toJson(student), Student.class);
        student4.setGPA(null);

        Student student5 = gson.fromJson(gson.toJson(student), Student.class);
        student5.setGPA(11.0);

        Student student6 = gson.fromJson(gson.toJson(student), Student.class);
        student6.setGPA(-1.0);


        Student student7 = gson.fromJson(gson.toJson(student), Student.class);
        student7.setLevel(null);

        return Stream.of(
                Arguments.of(student, true, "Student is OKay"),
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
        Gson gson = new Gson();
        Student s1 = new Student(
                "suenaf-asswer-dfffjj",
                "John",
                new Date(2000, Calendar.OCTOBER, 18),
                "Male",
                2.0,
                StudentLevel.GIOI
        );



        return Stream.of(
                Arguments.of(s1,"Create success")
//                Arguments.of(s1,"Create success with ")
        );
    }



    static Stream<Arguments> provideDataFor_CreateStudent_Failure(){
        Gson gson = new Gson();
        Student s1 = new Student(
                "1",
                "John",
                new Date(2000, Calendar.OCTOBER, 18),
                "Male",
                2.0,
                StudentLevel.GIOI
        );


        Student s2 = gson.fromJson(gson.toJson(s1), Student.class);
        s2.setId("2");

        return Stream.of(
                Arguments.of(s1, AppString.STUDENT_ID_IS_NOT_VALID,"Id is duplicated with other student that already in the database"),
                Arguments.of(s2, AppString.STUDENT_OBJECT_IS_NOT_VALID,"student is invalid")
        );
    }



        static Stream<Arguments> provideDataFor_UpdateUser_Success(){

        Gson gson = new Gson();
        Student student = new Student(
                "1",
                "John",
                new Date(2000, Calendar.OCTOBER, 18),
                "Male",
                2.0,
                StudentLevel.GIOI
        );
        return Stream.of(
                Arguments.of(student, "Success case"),
                Arguments.of(student, "Ca")
        );
    }
}
