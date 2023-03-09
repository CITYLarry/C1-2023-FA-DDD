package co.com.sofka.cityhotel.booking.domain.booking.commands;

import co.com.sofka.cityhotel.booking.domain.generic.Command;

public class AssignRoomCommand extends Command {

    public String roomId;
    public String roomNumber;
    public String bookingId;

    private AssignRoomCommand() {}

    public AssignRoomCommand(String roomId, String roomNumber, String bookingId) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.bookingId = bookingId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
}
