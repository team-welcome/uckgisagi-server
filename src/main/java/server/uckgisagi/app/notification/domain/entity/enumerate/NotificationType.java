package server.uckgisagi.app.notification.domain.entity.enumerate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import server.uckgisagi.common.model.EnumModel;

@Getter
@RequiredArgsConstructor
public enum NotificationType implements EnumModel {

    POKE("찌르기 알림"),
    FOLLOW("팔로우 알림"),
    ;

    private final String value;

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
