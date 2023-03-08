package co.com.sofka.cityhotel.booking.domain.client.values.identities;

import co.com.sofka.cityhotel.booking.domain.generic.Identity;

public class GuestId extends Identity {

    public GuestId() {}

    private GuestId(String uuid) {
        super(uuid);
    }

    public static GuestId of(String uuid) {
        return new GuestId(uuid);
    }
}
