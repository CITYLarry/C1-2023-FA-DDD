package co.com.sofka.cityhotel.booking.domain.client;

import co.com.sofka.cityhotel.booking.domain.client.entities.Address;
import co.com.sofka.cityhotel.booking.domain.client.entities.CreditCard;
import co.com.sofka.cityhotel.booking.domain.client.entities.Guest;
import co.com.sofka.cityhotel.booking.domain.client.events.AddedGuest;
import co.com.sofka.cityhotel.booking.domain.client.events.CreatedClient;
import co.com.sofka.cityhotel.booking.domain.client.events.EditedGuest;
import co.com.sofka.cityhotel.booking.domain.client.events.ModifiedAddress;
import co.com.sofka.cityhotel.booking.domain.client.events.RemovedGuest;
import co.com.sofka.cityhotel.booking.domain.client.events.ReplacedCreditCard;
import co.com.sofka.cityhotel.booking.domain.client.values.address.AddressValue;
import co.com.sofka.cityhotel.booking.domain.client.values.client.ClientEmail;
import co.com.sofka.cityhotel.booking.domain.client.values.client.ClientIdentification;
import co.com.sofka.cityhotel.booking.domain.client.values.client.ClientName;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardCcv;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardExpDate;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardNumber;
import co.com.sofka.cityhotel.booking.domain.client.values.guest.GuestEmail;
import co.com.sofka.cityhotel.booking.domain.client.values.guest.GuestName;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.ClientId;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.CreditCardId;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.GuestId;
import co.com.sofka.cityhotel.booking.domain.generic.AggregateRoot;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

import java.util.List;
import java.util.Objects;

public class Client extends AggregateRoot<ClientId> {

    protected ClientName clientName;
    protected ClientEmail clientEmail;
    protected ClientIdentification clientIdentification;
    protected Address address;
    protected CreditCard creditCard;
    protected List<Guest> guestList;

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

    public void modifyAddress(AddressValue address, ClientId clientId){
        Objects.requireNonNull(address);
        Objects.requireNonNull(clientId);
        appendChange(new ModifiedAddress(address, clientId)).apply();
    }

    public void addGuest(GuestId guestId, GuestName guestName, GuestEmail guestEmail, ClientId clientId) {
        Objects.requireNonNull(guestId);
        Objects.requireNonNull(guestName);
        Objects.requireNonNull(guestEmail);
        Objects.requireNonNull(clientId);
        appendChange(new AddedGuest(guestId, guestName, guestEmail, clientId)).apply();
    }

    public void removeGuest(GuestId guestId) {
        Objects.requireNonNull(guestId);
        appendChange(new RemovedGuest(guestId)).apply();
    }

    public void editGuestEmail(GuestId guestId, GuestEmail guestEmail) {
        Objects.requireNonNull(guestId);
        Objects.requireNonNull(guestEmail);
        appendChange(new EditedGuest(guestId, guestEmail)).apply();
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

    public List<Guest> guests() {
        return guestList;
    }
}
