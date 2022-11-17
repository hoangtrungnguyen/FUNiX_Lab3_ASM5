package org.example.studentmanager;

import org.example.constant.AppString;
import org.example.studentmanager.datalayer.StudentDao;
import org.example.studentmanager.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    StudentDao studentDao;

    public StudentManager(StudentDao studentDaoImpl){
        this.studentDao = studentDaoImpl;
    }


    public boolean createStudent(Student student) {
        if(!isStudentValid(student)){
            System.out.println(AppString.STUDENT_OBJECT_IS_NOT_VALID);
            return false;
        }
        try{
        studentDao.create(student);
        return true;
        }  catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateStudent(Student student){
        if(!isStudentValid(student)) return false;
        try{
            return studentDao.update(student);
        } catch (Exception e){
            return false;
        }
    }

    public List<Student> readAllStudent() {
        try{
            return studentDao.readAll();
        } catch (Exception e){
            return new ArrayList<Student>();
        }
    }


    public  List<Student> searchByName(String query ){
        try{
            return studentDao.findByName(query);
        } catch (Exception e){
            return new ArrayList<Student>();
        }
    }

    public boolean deleteById(String id){
        try{
            return studentDao.deleteById(id);
        } catch (Exception e){
            return false;
        }
    }



    public boolean isStudentValid(Student student){
        if(student.getId() == null){
            return false;
        }

        if(student.getName() == null){
            return false;
        }

        if(student.getDOB() == null){
            return false;
        }

        if(student.getGPA() == null){
            return false;
        }

        if(student.getGPA() < 0 || student.getGPA() > 10){
            return false;
        }

        if(student.getLevel() == null){
            return false;
        }

//        Pattern p = Pattern.compile("([Gg]iỏi)|([Kk]há)|([Tt]rung [Bb]ình)");
//        Matcher m2 = p.matcher(student.getLevel());
//        return m2.matches();
        return true;
    }

}
