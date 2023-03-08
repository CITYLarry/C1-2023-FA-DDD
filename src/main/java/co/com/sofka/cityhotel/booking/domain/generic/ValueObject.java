package co.com.sofka.cityhotel.booking.domain.generic;

import java.io.Serializable;

public interface ValueObject<T> extends Serializable {
    T value();
}
