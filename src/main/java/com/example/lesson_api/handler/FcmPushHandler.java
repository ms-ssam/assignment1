package com.example.lesson_api.handler;

import org.springframework.stereotype.Component;

@Component
public class FcmPushHandler implements PushHandlerContract {
    @Override
    public void send(String message) {
        System.out.println("FCM : " + message);
    }

    @Override
    public boolean isSendPossible(String userDeviceVersion) {
        return true;
    }
}
