package co.com.sofka.cityhotel.booking.domain.booking.values.room;

import co.com.sofka.cityhotel.booking.domain.generic.ValueObject;

public class RoomAvailable implements ValueObject<Boolean> {

    private final Boolean roomAvailable;

    public RoomAvailable(Boolean roomAvailable) {
        this.roomAvailable = roomAvailable;
    }

    @Override
    public Boolean value() {
        return roomAvailable;
    }
}
