package com.springSecurity.Security.Student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/vi/students")
public class StudentController {
    public static final List<Student> students = Arrays.asList(new Student(1,"James bond"),
           new Student(2,"Maria Jones"),
            new Student(3,"Anna Smith"));

    @GetMapping("{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId){
        return students.stream()
                .filter(student -> studentId.equals(student.getSdId()))
                .findFirst()
                .orElseThrow(()-> new IllegalStateException("Student "+studentId+" does not exist"));
    }
}
