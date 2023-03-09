package co.com.sofka.cityhotel.booking.domain.booking.values.room;

import co.com.sofka.cityhotel.booking.domain.generic.ValueObject;

public class RoomNumber implements ValueObject<String> {

    private final String roomNumber;

    public RoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String value() {
        return roomNumber;
    }
}
