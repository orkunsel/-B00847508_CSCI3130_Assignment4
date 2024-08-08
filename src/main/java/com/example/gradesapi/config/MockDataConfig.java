package com.example.gradesapi.config;

import com.example.gradesapi.model.Course;
import com.example.gradesapi.model.Student;
import com.example.gradesapi.repository.CourseRepository;
import com.example.gradesapi.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class MockDataConfig {

    @Bean
    CommandLineRunner runner(CourseRepository courseRepository, StudentRepository studentRepository) {
        return args -> {
            // Create courses
            Course course1 = new Course();
            course1.setName("Math 101");
            Course course2 = new Course();
            course2.setName("Science 101");
            Course course3 = new Course();
            course3.setName("History 101");

            courseRepository.saveAll(Arrays.asList(course1, course2, course3));

            // Create students
            Student student1 = new Student();
            student1.setName("Student One");
            Student student2 = new Student();
            student2.setName("Student Two");
            Student student3 = new Student();
            student3.setName("Student Three");
            Student student4 = new Student();
            student4.setName("Student Four");
            Student student5 = new Student();
            student5.setName("Student Five");
            Student student6 = new Student();
            student6.setName("Student Six");
            Student student7 = new Student();
            student7.setName("Student Seven");
            Student student8 = new Student();
            student8.setName("Student Eight");
            Student student9 = new Student();
            student9.setName("Student Nine");
            Student student10 = new Student();
            student10.setName("Student Ten");
            Student student11 = new Student();
            student11.setName("Student Eleven");
            Student student12 = new Student();
            student12.setName("Student Twelve");
            Student student13 = new Student();
            student13.setName("Student Thirteen");
            Student student14 = new Student();
            student14.setName("Student Fourteen");
            Student student15 = new Student();
            student15.setName("Student Fifteen");

            studentRepository.saveAll(Arrays.asList(
                    student1, student2, student3, student4, student5,
                    student6, student7, student8, student9, student10,
                    student11, student12, student13, student14, student15
            ));
        };
    }
}
