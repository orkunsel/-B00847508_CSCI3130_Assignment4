package com.example.gradesapi.service;

import com.example.gradesapi.model.Grade;
import com.example.gradesapi.model.Student;
import com.example.gradesapi.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.OptionalDouble;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public List<Grade> getGradesForStudentCourse(Long studentId, Long courseId) {
        // Ensure that the GradeRepository has this method
        return gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
    }

    /**
     * Calculate the average score for a given student in a specific course.
     */
    public Double calculateProjectedFinalGrade(Long studentId, Long courseId) {
        List<Grade> grades = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
        return grades.stream()
                .mapToDouble(Grade::getScore)
                .average()
                .orElse(0.0); // Default to 0 if no grades are found
    }

    /**
     * Calculate the required score on remaining assignments for a student to reach a target final grade.
     */
    public Double requiredGradeToTarget(Long studentId, Long courseId, Double targetGrade, int remainingAssignments) {
        Double currentAverage = calculateProjectedFinalGrade(studentId, courseId);
        // Assuming equal weight for all assignments and remaining assignments
        // (target - current) * total assignments / remaining = required average of remaining
        if (remainingAssignments > 0) {
            int totalAssignments = gradeRepository.findByStudentIdAndCourseId(studentId, courseId).size() + remainingAssignments;
            return (targetGrade * totalAssignments - currentAverage * (totalAssignments - remainingAssignments)) / remainingAssignments;
        }
        return null; // or throw an exception if no remaining assignments
    }

    /**
     * List students who are on track to achieve a target grade in a specific course.
     */
    public List<Student> studentsOnTrack(Long courseId, Double targetGrade) {
        // This method would require a custom query or a method to evaluate each student's current trajectory
        return null; // Placeholder for actual implementation
    }

    /**
     * Update or save a grade for a student in a specific course.
     */
    public Grade saveOrUpdateGrade(Grade grade) {
        return gradeRepository.save(grade);
    }
}
