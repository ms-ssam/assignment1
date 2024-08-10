package com.example.lesson_api.entity;

import com.example.lesson_api.enums.UserDeviceOS;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class Teacher extends User{

    @Id @GeneratedValue
    @Column(name = "teacher_id")
    private Long id;

//    @Enumerated(value = EnumType.STRING)
//    private UserDeviceOS teacherDeviceOS;
//
//    private String teacherDeviceVersion;

    @OneToMany(mappedBy = "teacher")
    private List<Lesson> lessonList = new ArrayList<>();

    @Override
    public String getSimpleName() {
        return "선생님";
    }
}
