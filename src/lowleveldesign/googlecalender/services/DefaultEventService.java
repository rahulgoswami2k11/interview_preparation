package lowleveldesign.googlecalender.services;

import lowleveldesign.googlecalender.model.Event;
import lowleveldesign.googlecalender.model.User;
import lowleveldesign.googlecalender.repositories.EventRepository;

import java.util.List;

public class DefaultEventService implements EventService {

    private EventRepository eventRepository;

    public DefaultEventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @Override
    public Event createEvent(String title, String eventDate, String startTime, String endTime,
                             List<User> participants, User createdBy) {

        Event event = Event.builder()
                .title(title)
                .eventDate(eventDate)
                .startTime(startTime)
                .endTime(endTime)
                .participants(participants)
                .createdBy(createdBy)
                .build();
        return eventRepository.createEvent(event);
    }

    @Override
    public List<Event> getEventsByDate(String eventDate, User user) {
        return eventRepository.getEventsByDate(eventDate, user);
    }

    @Override
    public Event updateEvent(Event oldEvent, Event newEvent) {
        eventRepository.deleteEvent(oldEvent);
        eventRepository.createEvent(newEvent);
        return newEvent;
    }
}
