package co.com.sofka.cityhotel.booking.domain.booking.events;

import co.com.sofka.cityhotel.booking.domain.booking.values.identities.RoomId;
import co.com.sofka.cityhotel.booking.domain.booking.values.room.RoomAvailable;
import co.com.sofka.cityhotel.booking.domain.booking.values.room.RoomNumber;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

public class AssignedRoom extends DomainEvent {

    private RoomId roomId;
    private RoomNumber roomNumber;
    private RoomAvailable roomAvailable;

    private AssignedRoom() {
        super("co.com.sofka.cityhotel.booking.domain.booking.events.AssignedRoom");
    }

    public AssignedRoom(RoomId roomId, RoomNumber roomNumber) {
        super("co.com.sofka.cityhotel.booking.domain.booking.events.AssignedRoom");
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomAvailable = new RoomAvailable(false);
    }

    public RoomId getRoomId() {
        return roomId;
    }

    public RoomNumber getRoomNumber() {
        return roomNumber;
    }

    public RoomAvailable getRoomAvailable() {
        return roomAvailable;
    }
}
