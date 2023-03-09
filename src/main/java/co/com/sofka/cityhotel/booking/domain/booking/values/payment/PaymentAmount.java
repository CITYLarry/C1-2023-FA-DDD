package co.com.sofka.cityhotel.booking.domain.booking.values.payment;

import co.com.sofka.cityhotel.booking.domain.generic.ValueObject;

public class PaymentAmount implements ValueObject<String> {

    private final String paymentAmount;

    public PaymentAmount(String paymentAmount){
        this.paymentAmount = paymentAmount;
    }

    @Override
    public String value() {
        return paymentAmount;
    }
}
