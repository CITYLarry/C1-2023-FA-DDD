package co.com.sofka.cityhotel.booking.domain.client.commands;

import co.com.sofka.cityhotel.booking.domain.generic.Command;

public class RemoveGuestCommand extends Command {

    private String guestId;
    private String clientId;

    private RemoveGuestCommand() {}

    public RemoveGuestCommand(String guestId, String clientId) {
        this.guestId = guestId;
        this.clientId = clientId;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
