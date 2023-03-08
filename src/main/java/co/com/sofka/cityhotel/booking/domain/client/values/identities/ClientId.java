package co.com.sofka.cityhotel.booking.domain.client.values.identities;

import co.com.sofka.cityhotel.booking.domain.generic.Identity;

public class ClientId extends Identity{

    public ClientId() {}

    private ClientId(String uuid) {
        super(uuid);
    }

    public static ClientId of(String uuid) {
        return new ClientId(uuid);
    }
}
