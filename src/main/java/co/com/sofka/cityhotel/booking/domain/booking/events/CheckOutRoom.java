package co.com.sofka.cityhotel.booking.domain.booking.events;

import co.com.sofka.cityhotel.booking.domain.booking.values.identities.RoomId;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

public class CheckOutRoom extends DomainEvent {

    private RoomId roomId;

    private CheckOutRoom() {
        super("co.com.sofka.cityhotel.booking.domain.booking.events.CheckOutRoom");
    }

    public CheckOutRoom(RoomId roomId) {
        super("co.com.sofka.cityhotel.booking.domain.booking.events.CheckOutRoom");
        this.roomId = roomId;
    }

    public RoomId getRoomId() {
        return roomId;
    }
}
