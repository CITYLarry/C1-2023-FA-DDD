package co.com.sofka.cityhotel.booking.domain.client.commands;

import co.com.sofka.cityhotel.booking.domain.generic.Command;

import java.time.LocalDate;

public class ReplaceCreditCardCommand extends Command {

    private String creditCardId;
    private String creditCardNumber;
    private LocalDate creditCardExpDate;
    private Integer creditCardCcv;
    private String clientId;

    private ReplaceCreditCardCommand() {}
    public ReplaceCreditCardCommand(String creditCardId,
                                    String creditCardNumber,
                                    LocalDate creditCardExpDate,
                                    Integer creditCardCcv,
                                    String clientId) {
        this.creditCardId = creditCardId;
        this.creditCardNumber = creditCardNumber;
        this.creditCardExpDate = creditCardExpDate;
        this.creditCardCcv = creditCardCcv;
        this.clientId = clientId;
    }

    public String getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(String creditCardId) {
        this.creditCardId = creditCardId;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public LocalDate getCreditCardExpDate() {
        return creditCardExpDate;
    }

    public void setCreditCardExpDate(LocalDate creditCardExpDate) {
        this.creditCardExpDate = creditCardExpDate;
    }

    public Integer getCreditCardCcv() {
        return creditCardCcv;
    }

    public void setCreditCardCcv(Integer creditCardCcv) {
        this.creditCardCcv = creditCardCcv;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
