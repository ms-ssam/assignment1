package com.example.lesson_api.service;

import com.example.lesson_api.entity.User;
import com.example.lesson_api.enums.UserDeviceOS;
import com.example.lesson_api.handler.ApnsPushHandler;
import com.example.lesson_api.handler.PushHandlerContract;
import com.example.lesson_api.handler.FcmPushHandler;
import com.example.lesson_api.handler.NhnPushHandler;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PushService {
    private Map<UserDeviceOS, PushHandlerContract> pushHandlerMappingInfo = new HashMap<>();

    private final FcmPushHandler fcmPushHandler;
    private final ApnsPushHandler apnsPushHandler;
    private final NhnPushHandler nhnPushHandler;

    @PostConstruct
    public void init() {
        pushHandlerMappingInfo.put(UserDeviceOS.ANDROID, fcmPushHandler);
        pushHandlerMappingInfo.put(UserDeviceOS.IOS, apnsPushHandler);
        pushHandlerMappingInfo.put(UserDeviceOS.CHROME, nhnPushHandler);
    }

    // 파라미터로 넘어온 사용자에게 push notification 전송 가능할 경우 전송
    public void sendNotification(User user) {
        PushHandlerContract pushHandler = getPushHandler(user.getUserDeviceOS());
        boolean isPossibleToFirePush = pushHandler.isSendPossible(user.getUserDeviceVersion());

        if(isPossibleToFirePush) {
            pushHandler.send(user.getSimpleName() + "! 수업이 시작되었습니다.");
        }
    }

    private PushHandlerContract getPushHandler(UserDeviceOS userDeviceOS) {
        return this.pushHandlerMappingInfo.getOrDefault(userDeviceOS, this.nhnPushHandler);
    }
}
