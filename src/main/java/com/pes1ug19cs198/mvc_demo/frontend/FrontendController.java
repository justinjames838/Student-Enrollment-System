package com.pes1ug19cs198.mvc_demo.frontend;

import java.util.List;

import com.pes1ug19cs198.mvc_demo.student.Student;
import com.pes1ug19cs198.mvc_demo.student.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(path = "/")
public class FrontendController {

    private StudentService studentService;
    @Autowired
    public FrontendController(StudentService studentService){
        this.studentService = studentService;
    }
    @GetMapping
    String studentPage(Model model){
        List<Student> students = this.studentService.getStudents();

        model.addAttribute("students",students);
        return("student");
    }
}
