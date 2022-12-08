package server.uckgisagi.app.store.domain.entity.enumerate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import server.uckgisagi.common.model.EnumModel;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum TagName implements EnumModel {

    GROCERY("식료품"),
    DETERGENT("세제"),
    BEAUTY("미용"),
    ETC("기타"),
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
