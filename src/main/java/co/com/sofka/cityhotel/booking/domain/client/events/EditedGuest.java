package co.com.sofka.cityhotel.booking.domain.client.events;

import co.com.sofka.cityhotel.booking.domain.client.values.guest.GuestEmail;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.GuestId;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

public class EditedGuest extends DomainEvent {

    private GuestId guestId;
    private GuestEmail guestEmail;

    private EditedGuest() {
        super("co.com.sofka.cityhotel.booking.domain.client.events.EditedGuest");
    }

    public EditedGuest(GuestId guestId, GuestEmail guestEmail) {
        super("co.com.sofka.cityhotel.booking.domain.client.events.EditedGuest");
        this.guestId = guestId;
        this.guestEmail = guestEmail;
    }

    public GuestId getGuestId() {
        return guestId;
    }

    public GuestEmail getGuestEmail() {
        return guestEmail;
    }
}
