package studentmanager;

import datalayer.MockStudentDAO;
import org.example.studentmanager.datalayer.StudentDao;
import org.example.studentmanager.model.Student;
import org.example.studentmanager.StudentManager;
import org.example.studentmanager.model.StudentLevel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class CreateStudentTest {
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
     * Test cases cho hàm {@link  #isStudentValidTest  } dùng kiểm tra xem dữ liệu student đã đúng chưa
     * trước khi thực hiện bất cứ một query nào
     *
     * @param student: Mock object
     * @param expected: Giá trị return mong muốn
     * @param message: Message trả về
     * 6 test cases
     *  1. Name not null -> {@link Student#getName()}} not null
     *  2. Date of birth is not null -> {@link Student#getDOB()} ()}}
     *  3. Gender is not null ->  {@link Student#getGender()} ()} ()}}
     *  4. GPA is range from 0.0 to 10.0 -> {@link Student#getGPA()} ()}
     *  5. Level is not null and have value "Giỏi", "Khá", "Trung bình" -> {@link Student#getLevel()}
     *  6. ID is not null -> {@link Student#getId()}
     */
    @ParameterizedTest
    @MethodSource("studentmanager.Helper#provideStudentObject_ForFunctionCheckIfStudentIsValid")
    void isStudentValidTest(Student student, boolean expected, String message){
        assertEquals(expected,studentManager.isStudentValid(student),message);
    }


    /**
     * @param student : mock student object
     * @param message : Message trả về
     * Student được insert thành công vào cơ sở dữ liệu
     */

    @ParameterizedTest
    @MethodSource("studentmanager.Helper#provideDataFor_CreateStudent_Success")
    void createStudentSuccess(Student student, String message)  {
        studentManager.createStudent(student);
        Student expectedStudent = studentDao.readById(student.getId());
        // do the actual test
        assertAll("Test if two students are equal",
                ()-> assertEquals(expectedStudent.getId(), student.getId()),
                ()-> assertEquals(expectedStudent.getName(), student.getName()),
                ()-> assertEquals(expectedStudent.getDOB(), student.getDOB()),
                ()-> assertEquals(expectedStudent.getGPA(), student.getGPA()),
                ()-> assertEquals(expectedStudent.getLevel(), student.getLevel()),
                ()-> assertEquals(expectedStudent.getGender(), student.getGender())
        );
    }

    /**
     * Test case cho trường hợp id bị trùng với một id khác trong cơ sở dữ liệu
     */
    @Test
    void createStudentFailure_WithInvalidId(){
        Student student = new Student("1", "John", new Date(2000, Calendar.OCTOBER, 18), "Male", 2.0, StudentLevel.GIOI);
        boolean isSuccess = studentManager.createStudent(student);
        assertFalse(isSuccess);
    }

    /**
     * Test case cho trường hợp object student không thỏa mãn các yêu cầu
     */
    @ParameterizedTest
    @MethodSource("studentmanager.Helper#provideInvalidStudentsObject")
    void createStudentFailure_WithInvalidFields(Student student){
        boolean isSuccess = studentManager.createStudent(student);
        assertFalse(isSuccess);
    }
}