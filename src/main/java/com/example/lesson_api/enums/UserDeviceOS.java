package com.example.lesson_api.enums;

import java.util.Arrays;
import java.util.List;

public enum UserDeviceOS {
    CHROME, IOS, ANDROID;

    // 22.1.2 vs 24.1.5
    // version1이 version2보다 작으면 return true
    // version1이 version2보다 크거나 같으면 return false
    public boolean isLessVersion(String version1, String version2) {
        List<String> ver1 = Arrays.stream(version1.split("\\.")).toList();  // 22.1.2 -> [22, 1, 2]
        List<String> ver2 = Arrays.stream(version2.split("\\.")).toList();

        while(ver1.isEmpty()) {
            String top_ver1_part = ver1.remove(0);
            String top_ver2_part = ver2.remove(0);

            if(Integer.parseInt(top_ver1_part) == Integer.parseInt(top_ver2_part))
                continue;
            else if(Integer.parseInt(top_ver1_part) < Integer.parseInt(top_ver2_part))
                return true;
            else
                return false;
        }
        return false;  // 동일한 버전
    }
}
