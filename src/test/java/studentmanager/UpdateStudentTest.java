package studentmanager;

import datalayer.MockStudentDAO;
import org.example.studentmanager.StudentManager;
import org.example.studentmanager.datalayer.StudentDao;
import org.example.studentmanager.model.Gender;
import org.example.studentmanager.model.Student;
import org.example.studentmanager.model.StudentLevel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Nhóm test case cho hàm update student
 */
public class UpdateStudentTest {

    static StudentManager studentManager;
    static StudentDao studentDao;

    @BeforeEach
    void setUpDatabase(){
        studentDao = new MockStudentDAO();
        studentManager = new StudentManager(studentDao);
        Helper.setUpStudentData( (MockStudentDAO) (studentDao));
    }

    @AfterEach
    void resetDatabase(){
        studentDao = new MockStudentDAO();
        studentManager = new StudentManager(studentDao);
        Helper.setUpStudentData( (MockStudentDAO) (studentDao));
    }


    /**
     *
     * @param student: Mock student
     * Trường hợp update thành công
     */
    @ParameterizedTest
    @MethodSource("studentmanager.Helper#provideDataFor_UpdateUser_Success")
    void updateUserTest_Success(Student student ) {
        studentManager.updateStudent(student);
        Student foundStudent = studentDao.readById(student.getId());
        assertAll("Test if two students are equal",
                ()-> assertEquals(foundStudent.getId(), student.getId()),
                ()-> assertEquals(foundStudent.getName(), student.getName()),
                ()-> assertEquals(foundStudent.getDOB(), student.getDOB()),
                ()-> assertEquals(foundStudent.getGPA(), student.getGPA()),
                ()-> assertEquals(foundStudent.getLevel(), student.getLevel()),
                ()-> assertEquals(foundStudent.getGender(), student.getGender())
        );
    }

    @ParameterizedTest
    @MethodSource("studentmanager.Helper#provideInvalidStudentsObject")
    void updateUserTest_InvalidStudentObject(Student student){
        boolean isSuccess = studentManager.updateStudent(student);
        assertFalse(isSuccess);
    }

    @Test
    void updateUserTest_NotFoundObject(){
        Student student =
                new Student("1123123", "John", LocalDate.of(2000, 10,18), Gender.MALE, 2.0, StudentLevel.GIOI);

        boolean isSuccess = studentManager.updateStudent(student);
        assertFalse(isSuccess);
    }


}
