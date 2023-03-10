package co.com.sofka.cityhotel.booking.domain.booking.events;

import co.com.sofka.cityhotel.booking.domain.booking.values.identities.BookingId;
import co.com.sofka.cityhotel.booking.domain.booking.values.identities.RoomId;
import co.com.sofka.cityhotel.booking.domain.booking.values.room.RoomNumber;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

public class AssignedRoom extends DomainEvent {

    private RoomId roomId;
    private RoomNumber roomNumber;

    private BookingId bookingId;


    private AssignedRoom() {
        super("co.com.sofka.cityhotel.booking.domain.booking.events.AssignedRoom");
    }

    public AssignedRoom(RoomId roomId, RoomNumber roomNumber, BookingId bookingId) {
        super("co.com.sofka.cityhotel.booking.domain.booking.events.AssignedRoom");
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.bookingId = bookingId;
    }

    public RoomId getRoomId() {
        return roomId;
    }

    public RoomNumber getRoomNumber() {
        return roomNumber;
    }

    public BookingId getBookingId() {
        return bookingId;
    }
}
