package server.uckgisagi.app.notification.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationConstants {
    public static final String POKE_TITLE = "🚨 헉-! 초-비상! 🚨";
    public static final String POKE_BODY = "%s 님이 회원님을 찔렀어요! 지구를 억지로 사랑하고 글을 남겨보세요!";
    public static final String POKE_MESSAGE = "%s 님이 회원님을 찔렀습니다";

    public static final String FOLLOW_TITLE = "%s 님이 팔로우했어요!";
    public static final String FOLLOW_BODY = "%s 님의 피드를 확인해보새요!";
    public static final String FOLLOW_MESSAGE = "%s 님이 회원님을 팔로우하기 시작했습니다";

    public static final String FAILURE_MESSAGE = "❌ 알림 전송 실패 ❌";
    public static final String SUCCESS_MESSAGE = "✅ 알림 전송 성공 🚀";
}
