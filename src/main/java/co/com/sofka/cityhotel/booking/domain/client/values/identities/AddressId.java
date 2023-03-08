package co.com.sofka.cityhotel.booking.domain.client.values.identities;

import co.com.sofka.cityhotel.booking.domain.generic.Identity;

public class AddressId extends Identity {

    public AddressId() {}

    private AddressId(String uuid) {
        super(uuid);
    }

    public static AddressId of(String uuid) {
        return new AddressId(uuid);
    }
}
