package co.com.sofka.cityhotel.booking.domain.booking.entities;

import co.com.sofka.cityhotel.booking.domain.booking.values.identities.PaymentId;
import co.com.sofka.cityhotel.booking.domain.booking.values.payment.PaymentAmount;
import co.com.sofka.cityhotel.booking.domain.generic.Entity;

public class Payment extends Entity<PaymentId> {

    private PaymentAmount paymentAmount;

    private Payment(PaymentId paymentId){
        super(paymentId);
    }

    private Payment(PaymentId paymentId, PaymentAmount paymentAmount) {
        super(paymentId);
        this.paymentAmount = paymentAmount;
    }

    public static Payment from(PaymentId paymentId, PaymentAmount paymentAmount) {
        return new Payment(paymentId, paymentAmount);
    }

    public String paymentAmount() {
        return paymentAmount.value();
    }

    public void updatePaymentAmount(PaymentAmount paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
