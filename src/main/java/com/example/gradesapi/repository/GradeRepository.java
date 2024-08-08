package com.example.gradesapi.repository;

import com.example.gradesapi.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudentIdAndCourseId(Long studentId, Long courseId);
}
