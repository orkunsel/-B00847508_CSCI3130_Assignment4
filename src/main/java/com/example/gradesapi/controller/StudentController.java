package com.example.gradesapi.controller;

import com.example.gradesapi.model.Student;
import com.example.gradesapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Get all students
    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.findAll();
        return ResponseEntity.ok(students);
    }

    // Get a single student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.findById(id);
        return ResponseEntity.ok(student);
    }

    // Create a new student
    @PostMapping("/")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student newStudent = studentService.save(student);
        return ResponseEntity.ok(newStudent);
    }

    // Update an existing student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.update(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    // Delete a student
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }

    // Calculate projected final grade for a student in a specific course
    @GetMapping("/{studentId}/courses/{courseId}/final-grade")
    public ResponseEntity<Double> getProjectedFinalGrade(@PathVariable Long studentId, @PathVariable Long courseId) {
        Double finalGrade = studentService.getProjectedFinalGrade(studentId, courseId);
        return ResponseEntity.ok(finalGrade);
    }

    // Calculate required target grade for future assignments to reach a certain goal
    @GetMapping("/{studentId}/courses/{courseId}/target-grade")
    public ResponseEntity<Double> getRequiredTargetGrade(@PathVariable Long studentId, @PathVariable Long courseId, @RequestParam Double targetGrade) {
        Double requiredGrade = studentService.getRequiredTargetGrade(studentId, courseId, targetGrade);
        return ResponseEntity.ok(requiredGrade);
    }
}
