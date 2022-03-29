package com.pes1ug19cs198.mvc_demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;



import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args->{
                Student kyle = new Student(
                    "Rahul",
                    LocalDate.of(2002,Month.FEBRUARY,14),
                    "rahul@something.com"
                );

                Student alex = new Student(
                    "Alex",
                    LocalDate.of(2002,Month.FEBRUARY,14),
                    "alex@something.com"
                );

                repository.saveAll(List.of(kyle,alex));
        };
    }
}
