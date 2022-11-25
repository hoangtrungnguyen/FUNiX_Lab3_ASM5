package datalayer;

import org.example.studentmanager.datalayer.StudentDao;
import org.example.studentmanager.model.Gender;
import org.example.studentmanager.model.Student;
import org.example.studentmanager.model.StudentLevel;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MockStudentDAO implements StudentDao {
    public List<Student> students;

    public MockStudentDAO(){
        students = initialize();
    }

    public static List<Student> initialize(){
         ArrayList<Student> students = new ArrayList<>();
         students.add(new Student("1", "John", LocalDate.of(2001, 12,21), Gender.MALE, 2.0, StudentLevel.GIOI));
         students.add(new Student("2", "Bason", LocalDate.of(2001, 8,22), Gender.MALE, 2.0, StudentLevel.GIOI));
         students.add(new Student("3", "Hannix", LocalDate.of(2001, 2,11), Gender.MALE, 2.0, StudentLevel.GIOI));
         students.add(new Student("4", "Hannah", LocalDate.of(2001, 5,10), Gender.MALE, 2.0, StudentLevel.GIOI));
         students.add(new Student("5", "Mason", LocalDate.of(2001, 3,24), Gender.MALE, 2.0, StudentLevel.GIOI));
         students.add(new Student("6", "Kaneki", LocalDate.of(2001, 4,22), Gender.MALE, 2.0, StudentLevel.GIOI));

         return students;
    }
    @Override
    public void create(Student student) {
        if(students.stream().anyMatch(other -> student.getId().equals(other.getId()))){
            throw new KeyAlreadyExistsException("Student already existed");
        }
        students.add(student);
    }

    @Override
    public Student readById(String id) {
        for(Student s : students){
            if(Objects.equals(s.getId(), id)){
                return s;
            }
        }
        return null;
    }

    @Override
    public boolean update(Student student) {
        Integer foundIndex = null ;
        for(int i = 0; i < students.size(); i ++){
            if(students.get(i).getId().equalsIgnoreCase(student.getId())){
                foundIndex = i;
                break;
            }
        }

        if(foundIndex != null) {
            students.set(foundIndex, student);
            return true;
        } else {
           return false;
        }
    }

    @Override
    public boolean delete(Student student) {
        return students.removeIf(e -> Objects.equals(e.getId(), student.getId()));
    }

    @Override
    public List<Student> readAll() {
        return students;
    }

    @Override
    public List<Student> findByName(String query) {
        List<Student> students = new ArrayList<>();
        for(Student student : this.students){
            if(student.getName().contains(query)){
                students.add(student);
            }
        }

        return students;
    }

    @Override
    public boolean deleteById(String id) {
        return students.removeIf(e -> Objects.equals(e.getId(), id));
    }


    public int getSize(){
        return students.size();
    }
}
