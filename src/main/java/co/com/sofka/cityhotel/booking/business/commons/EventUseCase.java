package co.com.sofka.cityhotel.booking.business.commons;

import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

import java.util.List;

public interface EventUseCase <E extends DomainEvent>{

    List<DomainEvent> apply(E event);
}
