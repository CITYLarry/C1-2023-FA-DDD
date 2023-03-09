package co.com.sofka.cityhotel.booking.domain.booking;

import co.com.sofka.cityhotel.booking.domain.booking.entities.Room;
import co.com.sofka.cityhotel.booking.domain.booking.events.AssignedRoom;
import co.com.sofka.cityhotel.booking.domain.booking.events.CheckOutRoom;
import co.com.sofka.cityhotel.booking.domain.booking.events.CreatedBooking;
import co.com.sofka.cityhotel.booking.domain.booking.values.identities.RoomId;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.ClientId;
import co.com.sofka.cityhotel.booking.domain.generic.EventChange;

import java.util.ArrayList;

public class BookingBehavior extends EventChange {

    public BookingBehavior(Booking booking) {

        apply((CreatedBooking event) -> {
            booking.clientId = ClientId.of(event.getClientId());
            booking.roomList = new ArrayList<>();
            booking.services = event.getServices();
            booking.payment = event.getPayment();
        });

        apply((AssignedRoom event) -> {
            booking.roomList.add(
                    Room.from(
                            event.getRoomId(),
                            event.getRoomNumber(),
                            event.getRoomAvailable()
                    )
            );
        });

        apply((CheckOutRoom event) -> {
            booking.roomList.removeIf(room -> room.identity().equals(event.getRoomId()));
        });
    }

}
