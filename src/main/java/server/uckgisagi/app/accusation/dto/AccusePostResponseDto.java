package server.uckgisagi.app.accusation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccusePostResponseDto {

    private Long postId;

    private Long userId;
}
