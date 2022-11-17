package datalayer;

import org.example.studentmanager.datalayer.exception.StudentNotFoundException;
import org.example.studentmanager.model.Student;
import org.example.studentmanager.datalayer.StudentDao;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MockStudentDAO implements StudentDao {
    public List<Student> students = new ArrayList<Student>();
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
        Student foundStudent;
        Integer foundIndex = null ;
        for(int i = 0; i < students.size(); i ++){
            if(students.get(i).getId().equalsIgnoreCase(student.getId())){
                foundStudent = students.get(i);
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
