package co.com.sofka.cityhotel.booking.domain.client.values.client;

import co.com.sofka.cityhotel.booking.domain.generic.ValueObject;

public class ClientName implements ValueObject<String> {

    private final String clientName;

    public ClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public String value() {
        return clientName;
    }
}
