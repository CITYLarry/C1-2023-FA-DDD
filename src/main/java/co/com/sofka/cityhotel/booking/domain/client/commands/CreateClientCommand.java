package co.com.sofka.cityhotel.booking.domain.client.commands;

import co.com.sofka.cityhotel.booking.domain.generic.Command;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class CreateClientCommand extends Command {

    private String clientId;
    private String clientName;
    private String clientEmail;
    private String clientIdentification;
    private String addressId;
    private String address;
    private String creditCardId;
    private String creditCardNumber;
    private LocalDate creditCardExpDate;
    private Integer creditCardCcv;

    private CreateClientCommand() {}

    public CreateClientCommand(String clientId,
                               String clientName,
                               String clientEmail,
                               String clientIdentification,
                               String addressId,
                               String address,
                               String creditCardId,
                               String creditCardNumber,
                               LocalDate creditCardExpDate,
                               Integer creditCardCcv) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.clientIdentification = clientIdentification;
        this.addressId = addressId;
        this.address = address;
        this.creditCardId = creditCardId;
        this.creditCardNumber = creditCardNumber;
        this.creditCardExpDate = creditCardExpDate;
        this.creditCardCcv = creditCardCcv;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientIdentification() {
        return clientIdentification;
    }

    public void setClientIdentification(String clientIdentification) {
        this.clientIdentification = clientIdentification;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
