package lowleveldesign.googlecalender.services;

import lowleveldesign.googlecalender.model.Event;
import lowleveldesign.googlecalender.model.User;

import java.util.List;

public interface EventService {
    Event createEvent(
            String title,
            String eventDate,
            String startTime,
            String endTime,
            List<User> participants,
            User createdBy
    );

    List<Event> getEventsByDate(String date, User user);

    Event updateEvent(Event oldEvent, Event newEvent);

}