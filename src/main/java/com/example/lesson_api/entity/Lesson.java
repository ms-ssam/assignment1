package com.example.lesson_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Lesson {

    @Id
    @GeneratedValue
    @Column(name = "lesson_id")
    private Long id;

    // 선생:학생 N:N -> 선생:수업 1:N, 학생:수업 1:N
    // Lesson을 중간에 둬서 N:N을 2개의 1:N으로 풀기
    // Lesson은 FK로 user_id, teacher_id (PK는 lesson_id로 자체 식별자)
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
