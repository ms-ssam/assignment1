package com.example.lesson_api.enumTest;

import com.example.lesson_api.enums.UserDeviceOS;
import org.junit.jupiter.api.Test;

public class EnumTest {
    @Test
    public void enumTest() {
        UserDeviceOS userDeviceOS = UserDeviceOS.CHROME;
        boolean isLessThan = userDeviceOS.isLessVersion("22.1.2", "24.1.5");
    }
}
