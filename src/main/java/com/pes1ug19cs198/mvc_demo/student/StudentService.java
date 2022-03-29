package com.pes1ug19cs198.mvc_demo.student;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student){
        System.out.println(student);
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("email exists");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("student with id "+ studentId+" doesn't exist");
        }
        studentRepository.deleteById(studentId);

    }

    @Transactional
    public void updateStudent(Long studentId,String name,String email){
        Student student = studentRepository.findById(studentId)
        .orElseThrow(
            ()->new IllegalStateException("Student with id "+studentId+" doesn't exist")
            );

        if(name!= null && name.length()>0 && !student.getName().equals(name)){
            student.setName(name);
        }

        if(email!= null && email.length()>0 && !student.getEmail().equals(email)){
            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
            if(studentByEmail.isPresent()){
            throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }

    }
}
