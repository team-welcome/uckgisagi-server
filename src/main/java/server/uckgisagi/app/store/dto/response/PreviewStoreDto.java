package server.uckgisagi.app.store.dto.response;

import lombok.*;
import server.uckgisagi.app.store.domain.entity.Store;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PreviewStoreDto {

    private Long storeId;
    private String storeName;
    private String address;
    private String imageUrl;

    @Builder(access = AccessLevel.PACKAGE)
    private PreviewStoreDto(final Long storeId, final String storeName, final String address, final String imageUrl) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.address = address;
        this.imageUrl = imageUrl;
    }

    public static PreviewStoreDto from(Store store) {
        return PreviewStoreDto.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .address(store.getAddress())
                .imageUrl(store.getImageUrl())
                .build();
    }
}
