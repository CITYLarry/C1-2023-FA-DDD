package co.com.sofka.cityhotel.booking.domain.booking.commands;

import co.com.sofka.cityhotel.booking.domain.generic.Command;

public class CheckOutRoomCommand extends Command {

    private String roomId;
    private String bookingId;

    private CheckOutRoomCommand() {}

    public CheckOutRoomCommand(String roomId, String bookingId) {
        this.roomId = roomId;
        this.bookingId = bookingId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
}
