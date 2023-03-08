package co.com.sofka.ramirez.larry.cityairlines.booking.domain.generic;

import java.io.Serializable;

public interface ValueObject<T> extends Serializable {
    T value();
}
