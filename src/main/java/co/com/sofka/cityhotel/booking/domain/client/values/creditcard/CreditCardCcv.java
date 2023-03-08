package co.com.sofka.cityhotel.booking.domain.client.values.creditcard;

import co.com.sofka.cityhotel.booking.domain.generic.ValueObject;

public class CreditCardCcv implements ValueObject<Integer> {

    private final Integer creditCardCcv;

    public CreditCardCcv(Integer creditCardCcv) {
        this.creditCardCcv = creditCardCcv;
    }

    @Override
    public Integer value() {
        return creditCardCcv;
    }
}
