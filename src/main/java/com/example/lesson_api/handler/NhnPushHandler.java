package com.example.lesson_api.handler;

public class NhnPushHandler implements BasePushHandler{
    @Override
    public void send(String message) {
        System.out.println("NHN : " + message);
    }
}
