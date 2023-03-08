package co.com.sofka.cityhotel.booking.business.commons;

import co.com.sofka.cityhotel.booking.domain.generic.Command;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

import java.util.List;

public interface CommandUseCase <C extends Command>{

    List<DomainEvent> apply(C command);
}
