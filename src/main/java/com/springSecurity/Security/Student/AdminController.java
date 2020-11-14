package com.springSecurity.Security.Student;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/vi/students")
public class AdminController {
    public static final List<Student> students = Arrays.asList(new Student(1,"James bond"),
            new Student(2,"Maria Jones"),
            new Student(3,"Anna Smith"));

    @GetMapping
    public List<Student> getAllStudents(){
        return students;
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        System.out.println(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Integer studentId){
        System.out.println(studentId);
    }

    @PutMapping(path = "{studetId}")
    public void updateStudent(@PathVariable("studentId") Integer studentId,Student student){
        System.out.println(String.format("%s %s",studentId,student));
    }
}
