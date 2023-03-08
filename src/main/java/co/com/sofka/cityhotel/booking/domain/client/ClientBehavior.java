package co.com.sofka.cityhotel.booking.domain.client;

import co.com.sofka.cityhotel.booking.domain.client.events.CreatedClient;
import co.com.sofka.cityhotel.booking.domain.generic.EventChange;

import java.util.HashSet;

public class ClientBehavior extends EventChange {

    public ClientBehavior(Client client) {
        apply((CreatedClient event) -> {
            client.clientName = event.getClientName();
            client.clientEmail = event.getClientEmail();
            client.clientIdentification = event.getClientIdentification();
            client.address = event.getAddress();
            client.creditCard = event.getCreditCard();
            client.guestSet = new HashSet<>();
        });
    }
}
