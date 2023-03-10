package co.com.sofka.cityhotel.booking.domain.booking.events;

import co.com.sofka.cityhotel.booking.domain.booking.values.identities.RoomId;
import co.com.sofka.cityhotel.booking.domain.booking.values.room.RoomAvailable;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

public class CheckInRoom extends DomainEvent {

    private RoomId roomId;
    private RoomAvailable roomAvailable;

    private CheckInRoom() {
        super("co.com.sofka.cityhotel.booking.domain.booking.events.CheckInRoom");
    }

    public CheckInRoom(RoomId roomId, RoomAvailable roomAvailable) {
        super("co.com.sofka.cityhotel.booking.domain.booking.events.CheckInRoom");
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
