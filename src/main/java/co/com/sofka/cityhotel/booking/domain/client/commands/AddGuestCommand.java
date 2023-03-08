package co.com.sofka.cityhotel.booking.domain.client.commands;

import co.com.sofka.cityhotel.booking.domain.generic.Command;

public class AddGuestCommand extends Command {

    private String guestId;
    private String guestName;
    private String guestEmail;
    private String clientId;

    private AddGuestCommand() {}

    public AddGuestCommand(String guestId, String guestName, String guestEmail, String clientId) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.guestEmail = guestEmail;
        this.clientId = clientId;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
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
