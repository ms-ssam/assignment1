package com.example.lesson_api.entity;

import com.example.lesson_api.enums.UserDeviceOS;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Getter
public abstract class User {
    @Enumerated(value = EnumType.STRING)  // 여기에도 이거 붙여야하나?
    private UserDeviceOS userDeviceOS;

    private String userDeviceVersion;

    public abstract String getSimpleName();
}
