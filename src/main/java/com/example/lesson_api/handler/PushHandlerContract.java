package com.example.lesson_api.handler;

public interface PushHandlerContract {
    /**
     * 푸시 메시지를 전송한다.
     * @param message
     */
    public void send(String message);

    /**
     * 사용자 디바이스 버전과 기준 버전을 비교하여 푸시 전송 가능 여부를 반환한다.
     * @param userDeviceVersion 유저의 디바이스 버전
     * @return boolean 푸시 전송 가능 여부
     */
    public boolean isSendPossible(String userDeviceVersion);
}
