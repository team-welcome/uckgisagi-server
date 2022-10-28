package server.uckgisagi.app.user.dto.response;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import server.uckgisagi.common.model.EnumModel;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum FollowStatus implements EnumModel {

    ACTIVE("팔로잉"),
    INACTIVE("팔로우"),
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
