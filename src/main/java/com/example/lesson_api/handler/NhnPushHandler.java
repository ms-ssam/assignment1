package com.example.lesson_api.handler;

import com.example.lesson_api.utils.VersionComparator;
import org.springframework.stereotype.Component;

@Component
public class NhnPushHandler implements PushHandlerContract {
    private final String standardVersion = "23.8.5";

    @Override
    public void send(String message) {
        System.out.println("NHN : " + message);
    }

    @Override
    public boolean isSendPossible(String userDeviceVersion) {
        return VersionComparator.of(userDeviceVersion).greaterThanOrEquals(standardVersion);
    }
}
