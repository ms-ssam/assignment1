package com.example.lesson_api.entity;

import com.example.lesson_api.enums.UserDeviceOS;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Student extends User {

    @Id
    @GeneratedValue
    @Column(name = "student_id")
    private Long id;

    @OneToMany(mappedBy = "student")
    private List<Lesson> lessonList = new ArrayList<>();

    @Override
    public String getSimpleName() {
        return "학생";
    }
}

