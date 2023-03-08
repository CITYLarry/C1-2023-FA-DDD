package co.com.sofka.cityhotel.booking.domain.client.values.address;

import co.com.sofka.cityhotel.booking.domain.generic.ValueObject;

public class AddressValue implements ValueObject<String> {

    private final String addressValue;

    public AddressValue(String addressValue) {
        this.addressValue = addressValue;
    }

    @Override
    public String value() {
        return addressValue;
    }
}
