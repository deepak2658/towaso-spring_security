package com.springSecurity.Security.Student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/vi/students")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AdminController {
    public static final List<Student> students = Arrays.asList(new Student(1,"James bond"),
            new Student(2,"Maria Jones"),
            new Student(3,"Anna Smith"));

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
    public List<Student> getAllStudents(){
        return students;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewStudent(@RequestBody Student student){
        System.out.println(student);
    }

    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentId") Integer studentId){
        System.out.println(studentId);
    }

    @PreAuthorize("hasAuthority('student:write')")
    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Integer studentId,Student student){
        System.out.println(String.format("%s %s",studentId,student));
    }
}
