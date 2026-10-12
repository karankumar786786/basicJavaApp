package me.oneorg.app.controllers;

import me.oneorg.app.models.Student;
import me.oneorg.app.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }
    @GetMapping("/{id}")
    public Student getById(@PathVariable("id") Integer id){
        return studentService.getById(id);
    }
    @GetMapping
    public Collection<Student> getAllStudents(){
        return studentService.getAllStudents();
    }
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable("id") Integer id,@RequestBody Student student){
        return  studentService.upadteStudent(id, student);
    }
    @DeleteMapping("/{id}")
    public Student deleteStudent(@PathVariable("id") Integer id){
        return studentService.deleteStudent(id);
    }
}
