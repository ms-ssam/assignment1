package com.example.lesson_api.handler;

public class ApnsPushHandler implements BasePushHandler{
    @Override
    public void send(String message) {
        System.out.println("APNS : " + message);
    }
}
