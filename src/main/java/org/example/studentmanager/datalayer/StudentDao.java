package org.example.studentmanager.datalayer;

import org.example.studentmanager.model.Student;

import java.sql.SQLDataException;
import java.util.List;

public interface StudentDao {

    void create(Student student);
    Student readById(String id);
    boolean update(Student student);
    boolean delete(Student student);

    List<Student> readAll();

    List<Student> findByName(String query);

    boolean deleteById(String id);

}
