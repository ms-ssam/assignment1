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

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class LessonService {

    private final PushService pushService;  // push notification을 발송하는 구현체
    private final LessonJpaRepository lessonJpaRepository;
    private final StudentJpaRepository studentJpaRepository;
    private final TeacherJpaRepository teacherJpaRepository;

    // 수업 만들기 == 수업 시작을 의미
    public Optional<Lesson> makeLesson(MakeLessonRequestDto request) {
        // 1) 전화 수업으로 만들지 화상 수업으로 만들지 ( -> OS에 따라 달라짐)
        Optional<Teacher> teacherOptional = teacherJpaRepository.findById(request.getTeacherId());
        Optional<Student> studentOptional = studentJpaRepository.findById(request.getStudentId());

        boolean userExistenceConditionNotSatisfied = !(teacherOptional.isPresent() && studentOptional.isPresent());

        if (userExistenceConditionNotSatisfied) {  // 요청한 학생이나 선생님이 존재하지 않음.
            return Optional.empty();
        }

        Teacher teacher = teacherOptional.get();
        Student student = studentOptional.get();

        Lesson lesson = Lesson.builder()  // 보통 생성자 잘 안쓰고 빌더로 생성한다?
                .student(student)
                .teacher(teacher)
                .build();
        
        lessonJpaRepository.save(lesson);

        teacher.getLessonList().add(lesson);  // 따로 메서드 만드는 게 낫나?
        student.getLessonList().add(lesson);

        pushService.sendNotification(teacher);
        pushService.sendNotification(student);

        return Optional.of(lesson);
    }
}
