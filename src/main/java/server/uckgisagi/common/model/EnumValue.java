package server.uckgisagi.common.model;

public class EnumValue {

    private final String key;
    private final String value;

    public EnumValue(EnumModel enumModel) {
        this.key = enumModel.getKey();
        this.value = enumModel.getValue();
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
