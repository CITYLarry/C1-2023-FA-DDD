package co.com.sofka.cityhotel.booking.domain.booking.events;

import co.com.sofka.cityhotel.booking.domain.booking.values.identities.RoomId;
import co.com.sofka.cityhotel.booking.domain.booking.values.room.RoomAvailable;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

public class FreeUpRoom extends DomainEvent {

    private RoomId roomId;
    private RoomAvailable roomAvailable;

    private FreeUpRoom() {
        super("co.com.sofka.cityhotel.booking.domain.booking.events.FreeUpRoom");
    }

    public FreeUpRoom(RoomId roomId, RoomAvailable roomAvailable) {
        super("co.com.sofka.cityhotel.booking.domain.booking.events.FreeUpRoom");
        this.roomId = roomId;
        this.roomAvailable = roomAvailable;
    }

    public RoomId getRoomId() {
        return roomId;
    }

    public RoomAvailable getRoomAvailable() {
        return roomAvailable;
    }
}
