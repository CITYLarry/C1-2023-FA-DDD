package co.com.sofka.cityhotel.booking.domain.client.events;

import co.com.sofka.cityhotel.booking.domain.client.entities.Address;
import co.com.sofka.cityhotel.booking.domain.client.entities.CreditCard;
import co.com.sofka.cityhotel.booking.domain.client.entities.Guest;
import co.com.sofka.cityhotel.booking.domain.client.values.client.ClientEmail;
import co.com.sofka.cityhotel.booking.domain.client.values.client.ClientIdentification;
import co.com.sofka.cityhotel.booking.domain.client.values.client.ClientName;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public class CreatedClient extends DomainEvent {

    private ClientName clientName;
    private ClientEmail clientEmail;
    private ClientIdentification clientIdentification;
    private Address address;
    private CreditCard creditCard;
    private List<Guest> guestList;

    private CreatedClient() {
        super("co.com.sofka.cityhotel.booking.domain.client.events.CreatedClient");
    }

    public CreatedClient(ClientName clientName,
                         ClientEmail clientEmail,
                         ClientIdentification clientIdentification,
                         Address address,
                         CreditCard creditCard) {
        super("co.com.sofka.cityhotel.booking.domain.client.events.CreatedClient");
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.clientIdentification = clientIdentification;
        this.address = address;
        this.creditCard = creditCard;
        this.guestList = new ArrayList<>();
    }

    public ClientName getClientName() {
        return clientName;
    }

    public ClientEmail getClientEmail() {
        return clientEmail;
    }

    public ClientIdentification getClientIdentification() {
        return clientIdentification;
    }

    public Address getAddress() {
        return address;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public List<Guest> getGuestSet() {
        return guestList;
    }
}
