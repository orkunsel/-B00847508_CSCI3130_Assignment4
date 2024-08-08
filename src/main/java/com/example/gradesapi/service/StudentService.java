package com.example.gradesapi.service;

import com.example.gradesapi.model.Student;
import com.example.gradesapi.model.Grade;  // Import the Grade model
import com.example.gradesapi.repository.StudentRepository;
import com.example.gradesapi.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Find all students
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Autowired
    private GradeRepository gradeRepository;

    // Find a single student by ID
    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    // Save a new student
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    // Update an existing student
    public Student update(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        student.setName(studentDetails.getName());
        // Other fields to update as needed

        return studentRepository.save(student);
    }

    // Delete a student by ID
    public void delete(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        studentRepository.delete(student);
    }

    // Calculate projected final grade for a student in a specific course
    public Double getProjectedFinalGrade(Long studentId, Long courseId) {
        List<Grade> grades = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
        return grades.stream()
                .mapToDouble(Grade::getScore)
                .average()
                .orElse(0.0);  // Default to 0 if no grades are found
    }

    // Calculate target grades needed for future assignments to reach a goal
    public Double getRequiredTargetGrade(Long studentId, Long courseId, Double targetGrade) {
        Double currentAverage = getProjectedFinalGrade(studentId, courseId);
        // Assuming future assignments can total to a maximum score of 100
        Double scoreNeeded = Math.max(0, targetGrade - currentAverage);
        return scoreNeeded;  // This could be divided by the number of remaining assignments if needed
    }
}
