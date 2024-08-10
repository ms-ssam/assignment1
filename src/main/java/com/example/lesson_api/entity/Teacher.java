package com.example.lesson_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Teacher extends User {

    @Id
    @GeneratedValue
    @Column(name = "teacher_id")
    private Long id;

    @OneToMany(mappedBy = "teacher")
    private List<Lesson> lessonList = new ArrayList<>();

    @Override
    public String getSimpleName() {
        return "선생님";
    }
}
