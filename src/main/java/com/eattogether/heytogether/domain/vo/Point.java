package com.eattogether.heytogether.domain.vo;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Point {

    public static Point DEFAULT_POINT = new Point(0);

    private static final int MIN_POINT = 0;
    private final int value;

    public Point(final int value) {
        validatePoint(value);
        this.value = value;
    }

    private void validatePoint(final int value) {
        if (value < MIN_POINT) {
            throw new IllegalArgumentException("포인트는 0원 이상만 될 수 있습니다.");
        }
    }

    public Point accumulate(final int amountValue) {
        return new Point(this.value + amountValue);
    }

    public Point use(final int amountValue) {
        validateUsingPoint(amountValue);
        return new Point(this.value - amountValue);
    }

    private void validateUsingPoint(final int amountValue) {
        if (amountValue > this.value) {
            throw new IllegalArgumentException("포인트가 부족합니다.");
        }
    }

    public int getPoint() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Point point = (Point) o;
        return value == point.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
