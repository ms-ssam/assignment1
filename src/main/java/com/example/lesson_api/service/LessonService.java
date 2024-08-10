package com.example.lesson_api.service;

import com.example.lesson_api.dto.MakeLessonRequestDto;
import com.example.lesson_api.entity.Lesson;
import com.example.lesson_api.entity.Student;
import com.example.lesson_api.entity.Teacher;
import com.example.lesson_api.repository.LessonJpaRepository;
import com.example.lesson_api.repository.StudentJpaRepository;
import com.example.lesson_api.repository.TeacherJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class LessonService {

    private final PushService pushService;
    private final LessonJpaRepository lessonJpaRepository;
    private final StudentJpaRepository studentJpaRepository;
    private final TeacherJpaRepository teacherJpaRepository;

    // 수업 만들기 == 수업 시작을 의미
    @Transactional(readOnly = false)
    public Optional<Lesson> makeLesson(MakeLessonRequestDto request) {
        Optional<Teacher> teacherOptional = teacherJpaRepository.findById(request.getTeacherId());
        Optional<Student> studentOptional = studentJpaRepository.findById(request.getStudentId());

        boolean userExistenceConditionNotSatisfied = !(teacherOptional.isPresent() && studentOptional.isPresent());

        if (userExistenceConditionNotSatisfied) {
            return Optional.empty();
        }

        Teacher teacher = teacherOptional.get();
        Student student = studentOptional.get();

        Lesson lesson = Lesson.builder()
                .student(student)
                .teacher(teacher)
                .build();
        
        lessonJpaRepository.save(lesson);

        pushService.sendNotification(teacher);
        pushService.sendNotification(student);

        return Optional.of(lesson);
    }
}
