package co.com.sofka.cityhotel.booking.domain.booking.values.identities;

import co.com.sofka.cityhotel.booking.domain.generic.Identity;

public class RoomId extends Identity {

    public RoomId() {}

    private RoomId(String uuid) {
        super(uuid);
    }

    public static RoomId of(String uuid) {
        return new RoomId(uuid);
    }
}
