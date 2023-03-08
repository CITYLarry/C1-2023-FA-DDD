package co.com.sofka.cityhotel.booking.domain.client.values.guest;

import co.com.sofka.cityhotel.booking.domain.generic.ValueObject;

public class GuestName implements ValueObject<String> {

    private final String guestName;

    public GuestName(String guestName) {
        this.guestName = guestName;
    }

    @Override
    public String value() {
        return guestName;
    }
}
