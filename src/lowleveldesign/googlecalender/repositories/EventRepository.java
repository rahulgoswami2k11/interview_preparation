package lowleveldesign.googlecalender.repositories;

import lowleveldesign.googlecalender.model.Event;
import lowleveldesign.googlecalender.model.User;

import java.util.List;

public interface EventRepository {
    Event createEvent(Event event);

    Event deleteEvent(Event event);

    List<Event> getEventsByDate(String eventDate, User user);

}