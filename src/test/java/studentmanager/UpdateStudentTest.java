package studentmanager;

import datalayer.MockStudentDAO;
import org.example.studentmanager.StudentManager;
import org.example.studentmanager.datalayer.StudentDao;
import org.example.studentmanager.model.Student;
import org.example.studentmanager.model.StudentLevel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Calendar;
import java.util.Date;

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
     * @param testMessage: Message sau khi test
     * Trường hợp update thành công
     */
    @ParameterizedTest
    @MethodSource("studentmanager.Helper#provideDataFor_UpdateUser_Success")
    void updateUserTest_Success(Student student, String testMessage ) {
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
    void updateUserTest_FailWith_InvalidStudentObject(Student student){
        boolean isSuccess = studentManager.updateStudent(student);
        assertFalse(isSuccess);
    }

    @Test
    void updateUserTest_FailWith_NotFoundObject(){
        Student student = new Student("1123123", "John", new Date(2000, Calendar.OCTOBER, 18), "Male", 2.0, StudentLevel.GIOI);
        boolean isSuccess = studentManager.updateStudent(student);
        assertFalse(isSuccess);
    }


}
