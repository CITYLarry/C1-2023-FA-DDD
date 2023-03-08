package co.com.sofka.cityhotel.booking.domain.client.values.guest;

import co.com.sofka.cityhotel.booking.domain.generic.ValueObject;

public class GuestEmail implements ValueObject<String> {

    private final String guestEmail;

    public GuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    @Override
    public String value() {
        return guestEmail;
    }
}
