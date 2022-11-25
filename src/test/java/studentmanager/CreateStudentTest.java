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
        studentDao = null ;
        studentManager = null;
    }


    /**
     * Test cases cho hàm   #isStudentValidTest  dùng kiểm tra xem dữ liệu student đã đúng chưa
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
    void isStudentValid_NormalCase(Student student, boolean expected, String message){
        assertEquals(expected,studentManager.isStudentValid(student),message);
    }


    /**
     * @param student : mock student object
     * Student được insert thành công vào cơ sở dữ liệu
     */

    @ParameterizedTest
    @MethodSource("studentmanager.Helper#provideDataFor_CreateStudent_Success")
    void createStudent_SuccessCase(Student student)  {
        studentManager.createStudent(student);
        Student expectedStudent = studentDao.readById(student.getId());
        assertSame(student,expectedStudent);
    }

    /**
     * Test case cho trường hợp id bị trùng với một id khác trong cơ sở dữ liệu
     */
    @Test
    void createStudent_withIdThatAlreadyExisted(){
        Student student = new Student("1", "John", LocalDate.of(2001, 12,21), Gender.MALE, 2.0, StudentLevel.GIOI);
        assertFalse(studentManager.createStudent(student));
    }

    /**
     * Test case cho trường hợp object student không thỏa mãn các yêu cầu
     */
    @ParameterizedTest
    @MethodSource("studentmanager.Helper#provideInvalidStudentsObject")
    void createStudent_WithInvalidFields(Student student){
        boolean isSuccess = studentManager.createStudent(student);
        assertFalse(isSuccess);
    }
}
