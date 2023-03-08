package co.com.sofka.cityhotel.booking.domain.client;

import co.com.sofka.cityhotel.booking.domain.client.entities.Address;
import co.com.sofka.cityhotel.booking.domain.client.entities.CreditCard;
import co.com.sofka.cityhotel.booking.domain.client.entities.Guest;
import co.com.sofka.cityhotel.booking.domain.client.events.CreatedClient;
import co.com.sofka.cityhotel.booking.domain.client.events.ReplacedCreditCard;
import co.com.sofka.cityhotel.booking.domain.client.values.client.ClientEmail;
import co.com.sofka.cityhotel.booking.domain.client.values.client.ClientIdentification;
import co.com.sofka.cityhotel.booking.domain.client.values.client.ClientName;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardCcv;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardExpDate;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardNumber;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.ClientId;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.CreditCardId;
import co.com.sofka.cityhotel.booking.domain.generic.AggregateRoot;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

import java.util.List;
import java.util.Objects;
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

    public Client(ClientId clientId,
                  ClientName clientName,
                  ClientEmail clientEmail,
                  ClientIdentification clientIdentification,
                  Address address,
                  CreditCard creditCard) {
        super(clientId);
        subscribe(new ClientBehavior(this));
        appendChange(new CreatedClient(clientName, clientEmail, clientIdentification, address, creditCard)).apply();
    }

    public static Client from(ClientId clientId, List<DomainEvent> events) {
        Client client = new Client(clientId);
        events.forEach(client::applyEvent);
        return client;
    }

    public void replaceCreditCard(CreditCardId creditCardId,
                                  CreditCardNumber creditCardNumber,
                                  CreditCardExpDate creditCardExpDate,
                                  CreditCardCcv creditCardCcv,
                                  ClientId clientId) {
        Objects.requireNonNull(creditCardId);
        Objects.requireNonNull(creditCardNumber);
        Objects.requireNonNull(creditCardExpDate);
        Objects.requireNonNull(creditCardCcv);
        appendChange(new ReplacedCreditCard(creditCardId, creditCardNumber, creditCardExpDate, creditCardCcv, clientId)).apply();
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
