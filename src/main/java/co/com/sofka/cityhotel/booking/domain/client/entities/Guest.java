package co.com.sofka.cityhotel.booking.domain.client.entities;

import co.com.sofka.cityhotel.booking.domain.client.values.guest.GuestEmail;
import co.com.sofka.cityhotel.booking.domain.client.values.guest.GuestName;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.GuestId;
import co.com.sofka.cityhotel.booking.domain.generic.Entity;

public class Guest extends Entity<GuestId> {

    private GuestName guestName;
    private GuestEmail guestEmail;

    private Guest(GuestId guestId) {
        super(guestId);
    }

    private Guest(GuestId guestId, GuestName guestName, GuestEmail guestEmail) {
        super(guestId);
        this.guestName = guestName;
        this.guestEmail = guestEmail;
    }

    public static Guest from(GuestId guestId, GuestName guestName, GuestEmail guestEmail) {
        return new Guest(guestId, guestName, guestEmail);
    }

    public String guestName() {
        return guestName.value();
    }

    public void updateGuestName(GuestName guestName) {
        this.guestName = guestName;
    }

    public String guestEmail() {
        return guestEmail.value();
    }

    public void updateGuestEmail(GuestEmail guestEmail) {
        this.guestEmail = guestEmail;
    }
}
