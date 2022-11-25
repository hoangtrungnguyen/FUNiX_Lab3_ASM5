package studentmanager;

import datalayer.MockStudentDAO;
import org.example.studentmanager.StudentManager;
import org.example.studentmanager.datalayer.StudentDao;
import org.example.studentmanager.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchStudentTest {
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
    void searchStudent_Success(){
        List<Student> students = studentManager.searchByName("Ng");
        assertTrue(students.size() > 0);
    }

    @Test
    void searchStudent_NotFound(){
        List<Student> students = studentManager.searchByName("Ngassssssss");
        assertEquals(0, students.size());
    }

}
