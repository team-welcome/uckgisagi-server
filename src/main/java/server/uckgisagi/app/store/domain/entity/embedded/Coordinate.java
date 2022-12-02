package server.uckgisagi.app.store.domain.entity.embedded;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Coordinate {

    private double xCoordinate;
    private double yCoordinate;

    public static Coordinate of(double xCoordinate, double yCoordinate) {
        return new Coordinate(xCoordinate, yCoordinate);
    }
}
