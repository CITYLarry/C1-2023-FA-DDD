package co.com.sofka.cityhotel.booking.domain.booking;

import co.com.sofka.cityhotel.booking.domain.booking.entities.Payment;
import co.com.sofka.cityhotel.booking.domain.booking.entities.Room;
import co.com.sofka.cityhotel.booking.domain.booking.entities.Services;
import co.com.sofka.cityhotel.booking.domain.booking.events.CreatedBooking;
import co.com.sofka.cityhotel.booking.domain.booking.values.identities.BookingId;
import co.com.sofka.cityhotel.booking.domain.booking.values.service.ServiceType;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.ClientId;
import co.com.sofka.cityhotel.booking.domain.generic.AggregateRoot;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

import java.util.List;

public class Booking extends AggregateRoot<BookingId> {

    protected ClientId clientId;
    protected List<Room> roomList;
    protected Services services;
    protected Payment payment;

    private Booking(BookingId bookingId) {
        super(bookingId);
        subscribe(new BookingBehavior(this));
    }

    public Booking(BookingId bookingId,
                   ClientId clientId,
                   Services services,
                   Payment payment) {
        super(bookingId);
        subscribe(new BookingBehavior(this));
        appendChange(new CreatedBooking(clientId, services, payment)).apply();
    }

    public static Booking from(BookingId bookingId, List<DomainEvent> events) {
        Booking booking = new Booking(bookingId);
        events.forEach(booking::applyEvent);
        return booking;
    }

    //Getters methods
    public String getClientId() {
        return clientId.value();
    }

    public List<Room> bookedRooms() {
        return roomList;
    }

    public List<ServiceType> hiredServices() {
        return services.services();
    }

    public String payment() {
        return payment.paymentAmount();
    }
}
