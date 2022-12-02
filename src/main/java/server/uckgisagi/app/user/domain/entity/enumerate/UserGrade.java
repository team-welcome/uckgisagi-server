package server.uckgisagi.app.user.domain.entity.enumerate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import server.uckgisagi.common.model.EnumModel;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserGrade implements EnumModel {

    SQUIRE("종자", 2),
    BARON("남작", 5),
    EARL("백작", 10),
    DUKE("공작", 19),
    LORD("영주", 32),
    KING("왕", 53),
    ;

    private final String value;
    private final int accumulate;

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }

    public int getAccumulate() {
        return accumulate;
    }
}
