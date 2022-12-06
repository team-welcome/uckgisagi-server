package server.uckgisagi.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomNicknameUtils {

    private static final List<String> NICK = new ArrayList<>(List.of(
            "벌목 현장에 눈물 흘리는", "쓰레기 줍는", "분리수거하는", "피눈물 흘리는",
            "잔반없는", "분칠하는", "텀블러 씻는", "에코백 쓰는", "헌혈하는",
            "재활용 하는", "집이 물에 잠기는", "설거지 하는", "골머리 앓는"));

    private static final List<String> NAME = new ArrayList<>(List.of(
            "조예족", "목도리 도마뱀", "판다", "북극곰", "펭귄", "포메라니안",
            "모기", "개미", "호랑이", "고양이",  "기린",  "삵", "캥거루",
            "몰디브 주민", "대학원생"));

    public static String generate() {
        shuffleRandomNickname();
        return NICK.get(0) + " " + NAME.get(0);
    }

    private static void shuffleRandomNickname() {
        Collections.shuffle(NICK);
        Collections.shuffle(NAME);
    }

}
