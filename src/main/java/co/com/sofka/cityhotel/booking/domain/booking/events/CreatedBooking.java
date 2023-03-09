package co.com.sofka.cityhotel.booking.domain.booking.events;

import co.com.sofka.cityhotel.booking.domain.booking.entities.Payment;
import co.com.sofka.cityhotel.booking.domain.booking.entities.Room;
import co.com.sofka.cityhotel.booking.domain.booking.entities.Services;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.ClientId;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public class CreatedBooking extends DomainEvent {

    private ClientId clientId;
    private List<Room> roomsList;
    private Services services;
    private Payment payment;

    private CreatedBooking() {
        super("co.com.sofka.cityhotel.booking.domain.booking.events.CreatedBooking");
    }

    public CreatedBooking(ClientId clientId,
                          Services services,
                          Payment payment) {
        super("co.com.sofka.cityhotel.booking.domain.booking.events.CreatedBooking");
        this.clientId = clientId;
        this.roomsList = new ArrayList<>();
        this.services = services;
        this.payment = payment;
    }

    public String getClientId() {
        return clientId.value();
    }

    public List<Room> getRoomsList() {
        return roomsList;
    }

    public Services getServices() {
        return services;
    }

    public Payment getPayment() {
        return payment;
    }
}
