package com.pes1ug19cs198.mvc_demo.student;


import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path= "/api/v1/student")
public class StudentController {
    private final StudentService studentService;
    

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping(consumes="application/x-www-form-urlencoded")
    public void registerNewStudent( @RequestParam String name,@RequestParam String email,@RequestParam String dob,HttpServletResponse httpResponse) throws IOException{
        LocalDate Dob = LocalDate.parse(dob);
        Student student = new Student(name,Dob,email);
         studentService.addNewStudent(student);
         httpResponse.sendRedirect("/");
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String email){
                    studentService.updateStudent(studentId, name, email);
    }

}
