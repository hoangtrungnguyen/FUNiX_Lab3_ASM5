package studentmanager;

import datalayer.MockStudentDAO;
import org.example.studentmanager.StudentManager;
import org.example.studentmanager.datalayer.StudentDao;
import org.example.studentmanager.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadStudentTest {

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

    @Test
    void readAllStudent(){
        List<Student> students = studentDao.readAll();
        assertEquals(( (MockStudentDAO) studentDao).getSize(), students.size());

    }


    @Nested
    class EmptyCase{

        @BeforeEach
        void setUpDatabase(){
            studentDao = new MockStudentDAO();
            studentManager = new StudentManager(studentDao);
        }

        @AfterEach
        void resetDatabase() {
            studentDao = new MockStudentDAO();
            studentManager = new StudentManager(studentDao);
        }
    }

}
