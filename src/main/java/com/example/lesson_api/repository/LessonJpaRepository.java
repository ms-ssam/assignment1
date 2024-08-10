package com.example.lesson_api.repository;

import com.example.lesson_api.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonJpaRepository extends JpaRepository<Lesson, Long> {
}
