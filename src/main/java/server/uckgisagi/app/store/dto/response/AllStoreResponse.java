package server.uckgisagi.app.store.dto.response;

import lombok.*;

import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AllStoreResponse {

    private List<PreviewStoreDto> mostPopularStore;
    private List<PreviewStoreDto> restStore;

    public static AllStoreResponse of(List<PreviewStoreDto> popular, List<PreviewStoreDto> rest) {
        return new AllStoreResponse(popular, rest);
    }

}
