package com.example.lesson_api.dto;

import com.example.lesson_api.enums.UserDeviceOS;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MakeLessonRequestDto {
    private Long studentId;

    @Enumerated(value = EnumType.STRING)
    private UserDeviceOS studentDeviceOS;

    private String studentDeviceVersion;

    private Long teacherId;

    @Enumerated(value = EnumType.STRING)
    private UserDeviceOS teacherDeviceOS;

    private String teacherDeviceVersion;
}
