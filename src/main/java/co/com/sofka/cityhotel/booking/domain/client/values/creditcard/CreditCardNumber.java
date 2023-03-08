package co.com.sofka.cityhotel.booking.domain.client.values.creditcard;

import co.com.sofka.cityhotel.booking.domain.generic.ValueObject;

public class CreditCardNumber implements ValueObject<String> {

    private final String creditCardNumber;

    public CreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @Override
    public String value() {
        return creditCardNumber;
    }
}
