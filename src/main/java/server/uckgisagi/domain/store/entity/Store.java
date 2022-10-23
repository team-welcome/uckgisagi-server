package server.uckgisagi.domain.store.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.uckgisagi.domain.common.AuditingTimeEntity;
import server.uckgisagi.domain.review.entity.Review;
import server.uckgisagi.domain.store.entity.embedded.Coordinate;
import server.uckgisagi.domain.store.entity.enumerate.TagName;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "web_site")
    private String webSite;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Embedded
    private Coordinate coordinate;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<StoreTag> storeTags = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Review> reviews = new ArrayList<>();

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void addStoreTags(List<Tag> tags) {
        for (Tag tag : tags) {
            this.addTag(tag);
        }
    }

    private void addTag(Tag tag) {
        StoreTag storeTag = StoreTag.of(this, tag);
        this.storeTags.add(storeTag);
    }

    public List<String> getStoreTagValue() {
        return storeTags.stream()
                .map(StoreTag::getTag)
                .map(Tag::getTagName)
                .map(TagName::getValue)
                .collect(Collectors.toList());
    }

}
