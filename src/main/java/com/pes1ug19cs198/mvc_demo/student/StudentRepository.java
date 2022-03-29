package com.pes1ug19cs198.mvc_demo.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("select s from Student s where s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    /*
    @Query("select s.id from Student s where s.email = ?1 and s.name = ?2")
    Optional<Long> getIdByEmailAndName(String email,String name);
    */
}
