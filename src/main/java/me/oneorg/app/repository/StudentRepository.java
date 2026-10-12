package me.oneorg.app.repository;

import me.oneorg.app.models.Student;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class StudentRepository {
    private final Map<Integer, Student> students = new HashMap<>();
    private Integer currentId = 1;

    public Student save(Student student){
        student.setId(currentId);
        students.put(currentId,student);
        currentId++;
        return student;
    }
    public Student findById(Integer id){
        return students.get(id);
    }
    public Collection<Student> findAll(){
        return students.values();
    }
    public Student updateStudent(Integer id,Student student){
        Student existingStudent = students.get(id);
        if (existingStudent == null) {
            return null;
        }
        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setMobile(student.getMobile());
        return existingStudent;
    }
    public Student delete(Integer id){
        return students.remove(id);
    }
}
