package co.com.sofka.cityhotel.booking.domain.client;

import co.com.sofka.cityhotel.booking.domain.client.entities.Address;
import co.com.sofka.cityhotel.booking.domain.client.entities.CreditCard;
import co.com.sofka.cityhotel.booking.domain.client.entities.Guest;
import co.com.sofka.cityhotel.booking.domain.client.values.client.ClientEmail;
import co.com.sofka.cityhotel.booking.domain.client.values.client.ClientIdentification;
import co.com.sofka.cityhotel.booking.domain.client.values.client.ClientName;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.ClientId;
import co.com.sofka.cityhotel.booking.domain.generic.AggregateRoot;

import java.util.Set;

public class Client extends AggregateRoot<ClientId> {

    protected ClientName clientName;
    protected ClientEmail clientEmail;
    protected ClientIdentification clientIdentification;
    protected Address address;
    protected CreditCard creditCard;
    protected Set<Guest> guestSet;

    private Client(ClientId clientId) {
        super(clientId);
        subscribe(new ClientBehavior(this));
    }


    //Getters methods
    public String clientName() {
        return clientName.value();
    }

    public String clientEmail() {
        return clientEmail.value();
    }

    public String clientIdentification() {
        return clientIdentification.value();
    }

    public String address() {
        return address.addressValue();
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public Set<Guest> guests() {
        return guestSet;
    }
}
