package co.com.sofka.cityhotel.booking.domain.booking.entities;

import co.com.sofka.cityhotel.booking.domain.booking.values.identities.RoomId;
import co.com.sofka.cityhotel.booking.domain.booking.values.room.RoomAvailable;
import co.com.sofka.cityhotel.booking.domain.booking.values.room.RoomNumber;
import co.com.sofka.cityhotel.booking.domain.generic.Entity;

public class Room extends Entity<RoomId> {

    private RoomNumber roomNumber;
    private RoomAvailable roomAvailable;

    private Room(RoomId roomId) {
        super(roomId);
    }

    private Room(RoomId roomId, RoomNumber roomNumber) {
        super(roomId);
        this.roomNumber = roomNumber;
    }

    public static Room from(RoomId roomId, RoomNumber roomNumber) {
        return new Room(roomId, roomNumber);
    }

    public String roomNumber() {
        return roomNumber.value();
    }

    public void changeRoom(RoomNumber roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Boolean isRoomAvailable() {
        return roomAvailable.value();
    }

    public void checkOutRoom(RoomAvailable roomAvailable) {
        this.roomAvailable = roomAvailable;
    }
}
