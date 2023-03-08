package co.com.sofka.cityhotel.booking.domain.client.values.client;

import co.com.sofka.cityhotel.booking.domain.generic.ValueObject;

public class ClientEmail implements ValueObject<String> {

    private final String clientEmail;

    public ClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    @Override
    public String value() {
        return clientEmail;
    }
}
