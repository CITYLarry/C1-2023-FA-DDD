package co.com.sofka.cityhotel.booking.domain.client.values.creditcard;

import co.com.sofka.cityhotel.booking.domain.generic.ValueObject;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class CreditCardExpDate implements ValueObject<LocalDate> {

    @DateTimeFormat(pattern = "yy-MM")
    private final LocalDate creditCardExpDate;

    public CreditCardExpDate(LocalDate creditCardExpDate) {
        this.creditCardExpDate = creditCardExpDate;
    }

    @Override
    public LocalDate value() {
        return creditCardExpDate;
    }
}
