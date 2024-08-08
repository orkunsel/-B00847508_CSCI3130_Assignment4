package com.example.gradesapi.controller;

import com.example.gradesapi.model.Grade;
import com.example.gradesapi.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.*;
import java.util.List;
import com.example.gradesapi.model.Grade;
import com.example.gradesapi.model.Student;
import com.example.gradesapi.model.Course;


@RestController
@RequestMapping("/v1/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    /**
     * Retrieves all grades for a specific student in a specific course.
     */
    @GetMapping("/{studentId}/{courseId}")
    public ResponseEntity<List<Grade>> getGradesByStudentAndCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        List<Grade> grades = gradeService.getGradesForStudentCourse(studentId, courseId);
        return ResponseEntity.ok(grades);
    }

    /**
     * Updates or creates a grade for a student in a course.
     */
    @PostMapping("/{studentId}/{courseId}")
    public ResponseEntity<Grade> updateOrCreateGrade(@PathVariable Long studentId, @PathVariable Long courseId, @RequestBody Grade grade) {
        grade.setStudent(new Student(studentId)); // Assuming Student has a constructor accepting an id
        grade.setCourse(new Course(courseId)); // Assuming Course has a constructor accepting an id
        Grade updatedGrade = gradeService.saveOrUpdateGrade(grade);
        return ResponseEntity.ok(updatedGrade);
    }

    /**
     * Calculates the projected final grade for a student in a specific course.
     */
    @GetMapping("/projected-final/{studentId}/{courseId}")
    public ResponseEntity<Double> getProjectedFinalGrade(@PathVariable Long studentId, @PathVariable Long courseId) {
        Double finalGrade = gradeService.calculateProjectedFinalGrade(studentId, courseId);
        return ResponseEntity.ok(finalGrade);
    }

    // Consider more endpoints for functionality like deleting a grade, calculating needed scores, etc.
}
