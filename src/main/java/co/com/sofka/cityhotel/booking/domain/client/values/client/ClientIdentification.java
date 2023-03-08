package co.com.sofka.cityhotel.booking.domain.client.values.client;

import co.com.sofka.cityhotel.booking.domain.generic.ValueObject;

public class ClientIdentification implements ValueObject<String> {

    private final String clientIdentification;

    public ClientIdentification(String clientIdentification) {
        this.clientIdentification = clientIdentification;
    }

    @Override
    public String value() {
        return clientIdentification;
    }
}
