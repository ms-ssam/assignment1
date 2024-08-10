package com.example.lesson_api.handler;

public class FcmPushHandler implements BasePushHandler{
    @Override
    public void send(String message) {
        System.out.println("FCM : " + message);
    }
}
