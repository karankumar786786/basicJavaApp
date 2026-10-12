package me.oneorg.app.service;

import me.oneorg.app.models.Student;
import me.oneorg.app.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student){
        return studentRepository.save(student);
    }
    public Student getById(Integer id){
        return studentRepository.findById(id);
    }
    public Student upadteStudent(Integer id,Student student){
        return studentRepository.updateStudent(id,student);
    }
    public Collection<Student> getAllStudents(){
        return studentRepository.findAll();
    }
    public Student deleteStudent(Integer id){
        return studentRepository.delete(id);
    }
}
