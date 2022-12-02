package server.uckgisagi.app.block.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlockUserDto {

    private Long blockUserId;

    public static BlockUserDto of(Long blockUserId) {
        return BlockUserDto.builder()
                .blockUserId(blockUserId)
                .build();
    }
}

