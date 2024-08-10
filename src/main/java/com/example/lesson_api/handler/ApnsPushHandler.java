package com.example.lesson_api.handler;

import org.springframework.stereotype.Component;

@Component
public class ApnsPushHandler implements PushHandlerContract {
    @Override
    public void send(String message) {
        System.out.println("APNS : " + message);
    }

    @Override
    public boolean isSendPossible(String userDeviceVersion) {
        return true;
    }
}
