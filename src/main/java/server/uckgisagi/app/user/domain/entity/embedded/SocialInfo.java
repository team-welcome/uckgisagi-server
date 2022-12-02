package server.uckgisagi.app.user.domain.entity.embedded;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.uckgisagi.app.user.domain.entity.enumerate.SocialType;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class SocialInfo {

    @Column(nullable = false, length = 200)
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private SocialType socialType;

    private SocialInfo(final String socialId, final SocialType socialType) {
        this.socialId = socialId;
        this.socialType = socialType;
    }

    public static SocialInfo of(String socialId, SocialType socialType) {
        return new SocialInfo(socialId, socialType);
    }
}
