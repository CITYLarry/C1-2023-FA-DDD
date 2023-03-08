package co.com.sofka.cityhotel.booking.domain.client.events;

import co.com.sofka.cityhotel.booking.domain.client.values.guest.GuestEmail;
import co.com.sofka.cityhotel.booking.domain.client.values.guest.GuestName;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.ClientId;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.GuestId;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

public class AddedGuest extends DomainEvent {

    private GuestId guestId;
    private GuestName guestName;
    private GuestEmail guestEmail;
    private ClientId clientId;

    private AddedGuest() {
        super("co.com.sofka.cityhotel.booking.domain.client.events.AddedGuest");
    }

    public AddedGuest(GuestId guestId,GuestName guestName, GuestEmail guestEmail, ClientId clientId) {
        super("co.com.sofka.cityhotel.booking.domain.client.events.AddedGuest");
        this.guestId = guestId;
        this.guestName = guestName;
        this.guestEmail = guestEmail;
        this.clientId = clientId;
    }

    public GuestId getGuestId() {
        return guestId;
    }

    public GuestName getGuestName() {
        return guestName;
    }

    public GuestEmail getGuestEmail() {
        return guestEmail;
    }

    public ClientId getClientId() {
        return clientId;
    }
}
