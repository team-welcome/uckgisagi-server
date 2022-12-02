package server.uckgisagi.app.post.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import server.uckgisagi.app.user.domain.entity.enumerate.UserGrade;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GradeResponse {

    private UserGrade grade;

    private GradeResponse(UserGrade grade) {
        this.grade = grade;
    }

    public static GradeResponse from(UserGrade grade) {
        return new GradeResponse(grade);
    }
}
