package co.com.sofka.cityhotel.booking.domain.client.entities;

import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardCcv;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardExpDate;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardNumber;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.CreditCardId;
import co.com.sofka.cityhotel.booking.domain.generic.Entity;

import java.time.LocalDate;

public class CreditCard extends Entity<CreditCardId> {

    private CreditCardNumber creditCardNumber;
    private CreditCardExpDate creditCardExpDate;
    private CreditCardCcv creditCardCcv;

    private CreditCard(CreditCardId creditCardId) {
        super(creditCardId);
    }

    private CreditCard(CreditCardId creditCardId, CreditCardNumber creditCardNumber, CreditCardExpDate creditCardExpDate, CreditCardCcv creditCardCcv) {
        super(creditCardId);
        this.creditCardNumber = creditCardNumber;
        this.creditCardExpDate = creditCardExpDate;
        this.creditCardCcv = creditCardCcv;
    }

    public String creditCardNumber() {
        return creditCardNumber.value();
    }

    public void updateCreditCardNumber(CreditCardNumber creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public LocalDate creditCardExpDate() {
        return creditCardExpDate.value();
    }

    public void updateCreditCardExpDate(CreditCardExpDate creditCardExpDate) {
        this.creditCardExpDate = creditCardExpDate;
    }

    public Integer creditCardCcv() {
        return creditCardCcv.value();
    }

    public void updateCreditCardCcv(CreditCardCcv creditCardCcv) {
        this.creditCardCcv = creditCardCcv;
    }
}
