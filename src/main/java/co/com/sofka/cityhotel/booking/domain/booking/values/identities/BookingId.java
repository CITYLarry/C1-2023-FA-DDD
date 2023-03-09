package co.com.sofka.cityhotel.booking.domain.booking.values.identities;

import co.com.sofka.cityhotel.booking.domain.generic.Identity;

public class BookingId extends Identity {

    public BookingId() {}

    private BookingId(String uuid) {
        super(uuid);
    }

    public static BookingId of(String uuid) {
        return new BookingId(uuid);
    }
}
