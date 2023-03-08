package co.com.sofka.cityhotel.booking.domain.client.events;

import co.com.sofka.cityhotel.booking.domain.client.values.address.AddressValue;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.ClientId;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

public class ModifiedAddress extends DomainEvent {

    private AddressValue addressValue;
    private ClientId clientId;

    private ModifiedAddress() {
        super("co.com.sofka.cityhotel.booking.domain.client.events.ModifiedAddress");
    }

    public ModifiedAddress(AddressValue addressValue, ClientId clientId) {
        super("co.com.sofka.cityhotel.booking.domain.client.events.ModifiedAddress");
        this.addressValue = addressValue;
        this.clientId = clientId;
    }

    public AddressValue getAddressValue() {
        return addressValue;
    }

    public ClientId getClientId() {
        return clientId;
    }
}
