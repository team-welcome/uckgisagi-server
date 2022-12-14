package server.uckgisagi.app.notification.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationConstants {
    public static final String POKE_TITLE = "π¨ ν-! μ΄-λΉμ! π¨";
    public static final String POKE_BODY = "%s λμ΄ νμλμ μ°λ μ΄μ! μ§κ΅¬λ₯Ό μ΅μ§λ‘ μ¬λνκ³  κΈμ λ¨κ²¨λ³΄μΈμ!";
    public static final String POKE_MESSAGE = "%s λμ΄ νμλμ μ°λ μ΅λλ€";

    public static final String FOLLOW_TITLE = "%s λμ΄ νλ‘μ°νμ΄μ!";
    public static final String FOLLOW_BODY = "%s λμ νΌλλ₯Ό νμΈν΄λ³΄μμ!";
    public static final String FOLLOW_MESSAGE = "%s λμ΄ νμλμ νλ‘μ°νκΈ° μμνμ΅λλ€";

    public static final String FAILURE_MESSAGE = "β μλ¦Ό μ μ‘ μ€ν¨ β";
    public static final String SUCCESS_MESSAGE = "β μλ¦Ό μ μ‘ μ±κ³΅ π";
}
