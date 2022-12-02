package server.uckgisagi.app.store.dto.response;

import lombok.*;
import server.uckgisagi.app.store.domain.entity.Store;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OneStoreResponse {

    private Long storeId;
    private String storeName;
    private String address;
    private String phoneNumber;
    private String webSite;
    private String description;
    private String imageUrl;
    private List<String> tags = new ArrayList<>();

    @Builder(access = AccessLevel.PACKAGE)
    private OneStoreResponse(final Long storeId, final String storeName, final String address, final String phoneNumber,
                             final String webSite, final String description, final String imageUrl) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.webSite = webSite;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public static OneStoreResponse from(Store store) {
        OneStoreResponse response = OneStoreResponse.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .address(store.getAddress())
                .phoneNumber(store.getPhoneNumber())
                .webSite(store.getWebSite())
                .description(store.getDescription())
                .imageUrl(store.getImageUrl())
                .build();
        response.tags.addAll(store.getStoreTagValue());
        return response;
    }
}
