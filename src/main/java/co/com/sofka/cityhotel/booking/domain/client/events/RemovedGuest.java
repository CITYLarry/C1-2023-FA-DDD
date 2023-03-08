package co.com.sofka.cityhotel.booking.domain.client.events;

import co.com.sofka.cityhotel.booking.domain.client.values.identities.ClientId;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.GuestId;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

public class RemovedGuest extends DomainEvent {

    private GuestId guestId;

    private RemovedGuest() {
        super("co.com.sofka.cityhotel.booking.domain.client.events.RemovedGuest");
    }

    public RemovedGuest(GuestId guestId) {
        super("co.com.sofka.cityhotel.booking.domain.client.events.RemovedGuest");
        this.guestId = guestId;
    }

    public GuestId getGuestId() {
        return guestId;
    }
}
