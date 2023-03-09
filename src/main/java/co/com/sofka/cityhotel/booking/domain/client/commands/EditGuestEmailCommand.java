package co.com.sofka.cityhotel.booking.domain.client.commands;

import co.com.sofka.cityhotel.booking.domain.generic.Command;

public class EditGuestEmailCommand extends Command {

    private String guestId;
    private String guestEmail;
    private String clientId;

    public EditGuestEmailCommand(String guestId, String guestEmail, String clientId) {
        this.guestId = guestId;
        this.guestEmail = guestEmail;
        this.clientId = clientId;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
