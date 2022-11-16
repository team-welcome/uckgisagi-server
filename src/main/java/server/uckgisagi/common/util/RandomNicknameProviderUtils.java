package server.uckgisagi.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomNicknameProviderUtils {

    public static String getRandomNickname() {
        List<String> nick = new ArrayList<>(List.of("개열받은 ", "미친 개딱딱한 돌빵먹는 ", "호구잡히는 ", "악플다는 ", "평화주의자 ", "급똥 마려운 ", "분노 조절 장애 ", "분노 조절 마스터 "));
        List<String> name = new ArrayList<>(List.of("스탈린", "호갱", "김삿갓", "신사임당", "나이팅게일", "히틀러", "간디", "나폴레옹", "귀도 반 로섬"));
        Collections.shuffle(nick);
        Collections.shuffle(name);
        return nick.get(0) + name.get(0);
    }

}
