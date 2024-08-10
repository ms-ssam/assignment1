package com.example.lesson_api.service;

import com.example.lesson_api.entity.Student;
import com.example.lesson_api.entity.Teacher;
import com.example.lesson_api.entity.User;
import com.example.lesson_api.enums.UserDeviceOS;
import com.example.lesson_api.handler.ApnsPushHandler;
import com.example.lesson_api.handler.BasePushHandler;
import com.example.lesson_api.handler.FcmPushHandler;
import com.example.lesson_api.handler.NhnPushHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service  // 이거 달아주면 @Component 생략 가능?
@RequiredArgsConstructor
public class PushService {

    private final FcmPushHandler fcmPushHandler;
    private final ApnsPushHandler apnsPushHandler;
    private final NhnPushHandler nhnPushHandler;

    // 파라미터로 넘어온 사용자에게 push notification 전송 가능할 경우 전송
    public void sendNotification(User user) {

        boolean send_condition = isSendable(user.getUserDeviceOS(), user.getUserDeviceVersion());

        BasePushHandler pushHandler = getPushHandler(user.getUserDeviceOS());

        if(send_condition) {
            pushHandler.send(user.getSimpleName() + "! 수업이 시작되었습니다.");
        }
    }

    // 기기와 버전이 주어지면 push notification을 보낼 수 있는지 없는지 return
    public boolean isSendable(UserDeviceOS userDeviceOS, String userDeviceVersion) {

        if(userDeviceOS == UserDeviceOS.CHROME && userDeviceOS.isLessVersion(userDeviceVersion, "23.8.5")) {
            return false;  // CHROME인데 23.8.5 버전보다 이전 버전 사용하면 push notification X
        }
        return true;  // 그 외의 모든 경우엔 push notification
    }

    // 기기에 맞는 push handler system return
    public BasePushHandler getPushHandler(UserDeviceOS userDeviceOS) {

        if(userDeviceOS == UserDeviceOS.IOS)
            return new ApnsPushHandler();
        else if(userDeviceOS == UserDeviceOS.ANDROID)
            return new FcmPushHandler();
        else
            return new NhnPushHandler();
    }
}
