package co.com.sofka.cityhotel.booking.business.commons;

import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventsRepository {

    DomainEvent saveEvent(DomainEvent event);

    List<DomainEvent> findAggregateRootId(String aggregateRootId);
}
