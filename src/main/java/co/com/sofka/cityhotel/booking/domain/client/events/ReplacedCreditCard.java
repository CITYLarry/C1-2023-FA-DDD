package co.com.sofka.cityhotel.booking.domain.client.events;

import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardCcv;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardExpDate;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardNumber;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.ClientId;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.CreditCardId;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

public class ReplacedCreditCard extends DomainEvent {

    private CreditCardId creditCardId;
    private CreditCardNumber creditCardNumber;
    private CreditCardExpDate creditCardExpDate;
    private CreditCardCcv creditCardCcv;
    private ClientId clientId;

    private ReplacedCreditCard() {
        super("co.com.sofka.cityhotel.booking.domain.client.events.ReplacedCreditCard");
    }

    public ReplacedCreditCard(CreditCardId creditCardId,
                              CreditCardNumber creditCardNumber,
                              CreditCardExpDate creditCardExpDate,
                              CreditCardCcv creditCardCcv,
                              ClientId clientId) {
        super("co.com.sofka.cityhotel.booking.domain.client.events.ReplacedCreditCard");
        this.creditCardId = creditCardId;
        this.creditCardNumber = creditCardNumber;
        this.creditCardExpDate = creditCardExpDate;
        this.creditCardCcv = creditCardCcv;
        this.clientId = clientId;
    }

    public CreditCardId getCreditCardId() {
        return creditCardId;
    }

    public CreditCardNumber getCreditCardNumber() {
        return creditCardNumber;
    }

    public CreditCardExpDate getCreditCardExpDate() {
        return creditCardExpDate;
    }

    public CreditCardCcv getCreditCardCcv() {
        return creditCardCcv;
    }

    public ClientId getClientId() {
        return clientId;
    }
}
