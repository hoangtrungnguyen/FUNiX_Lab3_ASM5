package org.example.studentmanager.datalayer;

import org.example.studentmanager.model.Student;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.sql.SQLDataException;
import java.util.List;

public class StudentDaoImpl implements  StudentDao{
    DataSource source = DataSource.getInstance();

    @Override
    public void create(Student student) {
//        source.conn.prepareStatement();
        throw new KeyAlreadyExistsException("Student already existed");
    }

    @Override
    public Student readById(String id) {
        return null;
    }

    @Override
    public boolean update(Student student) {
        return false;
    }

    @Override
    public boolean delete(Student student) {
        return false;
    }

    @Override
    public List<Student> readAll() {
        return null;
    }

    @Override
    public List<Student> findByName(String query) {
        return null;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }
}
