package co.com.sofka.cityhotel.booking.domain.booking;

import co.com.sofka.cityhotel.booking.domain.booking.entities.Payment;
import co.com.sofka.cityhotel.booking.domain.booking.entities.Room;
import co.com.sofka.cityhotel.booking.domain.booking.entities.Services;
import co.com.sofka.cityhotel.booking.domain.booking.events.AssignedRoom;
import co.com.sofka.cityhotel.booking.domain.booking.events.CheckOutRoom;
import co.com.sofka.cityhotel.booking.domain.booking.events.CreatedBooking;
import co.com.sofka.cityhotel.booking.domain.booking.events.FreeUpRoom;
import co.com.sofka.cityhotel.booking.domain.booking.events.HiredService;
import co.com.sofka.cityhotel.booking.domain.booking.values.identities.BookingId;
import co.com.sofka.cityhotel.booking.domain.booking.values.identities.RoomId;
import co.com.sofka.cityhotel.booking.domain.booking.values.room.RoomAvailable;
import co.com.sofka.cityhotel.booking.domain.booking.values.room.RoomNumber;
import co.com.sofka.cityhotel.booking.domain.booking.values.service.ServiceType;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.ClientId;
import co.com.sofka.cityhotel.booking.domain.generic.AggregateRoot;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

import java.util.List;
import java.util.Objects;

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

    public void assignRoom(RoomId roomId, RoomNumber roomNumber) {
        Objects.requireNonNull(roomId);
        Objects.requireNonNull(roomNumber);
        appendChange(new AssignedRoom(roomId, roomNumber)).apply();
    }

    public void checkOutRoom(RoomId roomId, BookingId bookingId) {
        Objects.requireNonNull(roomId);
        appendChange(new CheckOutRoom(roomId, bookingId)).apply();
    }

    public void hireService(ServiceType serviceType) {
        Objects.requireNonNull(serviceType);
        appendChange(new HiredService(serviceType)).apply();
    }

    public void freeUpRoom(RoomId roomId, RoomAvailable roomAvailable) {
        Objects.requireNonNull(roomId);
        Objects.requireNonNull(roomAvailable);
        appendChange(new FreeUpRoom(roomId, roomAvailable)).apply();
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
