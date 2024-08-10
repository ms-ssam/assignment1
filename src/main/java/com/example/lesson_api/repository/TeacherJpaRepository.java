package com.example.lesson_api.repository;

import com.example.lesson_api.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherJpaRepository extends JpaRepository<Teacher, Long> {
}
