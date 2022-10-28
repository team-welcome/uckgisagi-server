package server.uckgisagi.domain.user.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.uckgisagi.domain.common.AuditingTimeEntity;
import server.uckgisagi.domain.follow.entity.Follow;
import server.uckgisagi.domain.post.entity.Post;
import server.uckgisagi.domain.user.entity.embedded.SocialInfo;
import server.uckgisagi.domain.user.entity.enumerate.SocialType;
import server.uckgisagi.domain.user.entity.enumerate.UserGrade;
import server.uckgisagi.domain.user.entity.enumerate.UserStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Embedded
    private SocialInfo socialInfo;

    @Column(nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserGrade grade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private UserStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "token_id")
    private Token token;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Post> posts = new ArrayList<>();

    /**
     * 나를 팔로우하는 유저 List
     */
    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Follow> followers = new ArrayList<>(); // 여기 있는 followee 는 다 this(User), follower 는 나를 팔로우하는 애들

    /**
     * 내가 팔로우하는 유저 List
     */
    @OneToMany(mappedBy = "followee", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Follow> followings = new ArrayList<>(); // 여기 있는 followee 는 다 내가 팔로우하는 friend, follower 는 this(User)

    private User(final String socialId, final SocialType socialType, final String nickname) {
        this.socialInfo = SocialInfo.of(socialId, socialType);
        this.nickname = nickname;
        this.grade = UserGrade.SQUIRE;
        this.status = UserStatus.ACTIVE;
    }

    public static User newInstance(String socialId, SocialType socialType, String nickname) {
        return new User(socialId, socialType, nickname);
    }

    public void setTokenInfo(Token token) {
        this.token = token;
    }

    public String getUserFcmToken() {
        return this.token.getFcmToken();
    }

    /**
     * 나를 팔로우하는 유저 추가
     * @param follower 나를 팔로우하는 유저
     */
    public void addFollower(User follower) {
        this.followers.add(Follow.newInstance(this, follower));
    }

    /**
     * 내가 팔로우하는 유저 추가
     * @param target 내가 팔로우하는 유저
     */
    public void addFollowing(User target) {
        this.followings.add(Follow.newInstance(target, this));
    }

    public List<User> getMyFollowers() {
        return this.followers.stream()
                .map(Follow::getFollower)
                .collect(Collectors.toList());
    }

    public List<User> getMyFollowings() {
        return this.followings.stream()
                .map(Follow::getFollowee)
                .collect(Collectors.toList());
    }

    void addPosts(Post post) {
        this.posts.add(post);
    }

    void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void changeGrade(int count) {
        switch (count) {
            case 5:
                this.grade = UserGrade.BARON;
                break;
            case 10:
                this.grade = UserGrade.EARL;
                break;
            case 19:
                this.grade = UserGrade.DUKE;
                break;
            case 32:
                this.grade = UserGrade.LORD;
                break;
            case 53:
                this.grade = UserGrade.KING;
                break;
        }
    }

}
