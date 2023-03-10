package co.com.sofka.cityhotel.booking.domain.booking.events;

import co.com.sofka.cityhotel.booking.domain.booking.values.identities.BookingId;
import co.com.sofka.cityhotel.booking.domain.booking.values.identities.RoomId;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

public class CheckOutRoom extends DomainEvent {

    private RoomId roomId;
    private BookingId bookingId;

    private CheckOutRoom() {
        super("co.com.sofka.cityhotel.booking.domain.booking.events.CheckOutRoom");
    }

    public CheckOutRoom(RoomId roomId, BookingId bookingId) {
        super("co.com.sofka.cityhotel.booking.domain.booking.events.CheckOutRoom");
        this.roomId = roomId;
        this.bookingId = bookingId;
    }

    public RoomId getRoomId() {
        return roomId;
    }

    public BookingId getBookingId() {
        return bookingId;
    }
}
