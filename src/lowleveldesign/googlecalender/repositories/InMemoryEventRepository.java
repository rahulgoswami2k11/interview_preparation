package lowleveldesign.googlecalender.repositories;

import lowleveldesign.googlecalender.model.Event;
import lowleveldesign.googlecalender.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryEventRepository implements EventRepository {
    private Map<User, Map<String, List<Event>>> userEventMapping;

    public InMemoryEventRepository() {
        this.userEventMapping = new HashMap<>();
    }
    @Override
    public Event createEvent(Event event) {
        String eventDate = event.getEventDate();
        for(User participant: event.getParticipants()) {
            Map<String, List<Event>> dateEvents = userEventMapping.getOrDefault(participant, new HashMap<>());
            List<Event> events = dateEvents.getOrDefault(eventDate, new ArrayList<>());
            events.add(event);
            dateEvents.put(eventDate, events);
            userEventMapping.put(participant, dateEvents);
        }

        return event;
    }

    @Override
    public Event deleteEvent(Event event) {
        String eventDate = event.getEventDate();
        for(User participant: event.getParticipants()) {
            Map<String, List<Event>> dateEvents = userEventMapping.getOrDefault(participant, new HashMap<>());
            List<Event> events = dateEvents.getOrDefault(eventDate, new ArrayList<>());
            events.remove(event);
            dateEvents.put(eventDate, events);
            userEventMapping.put(participant, dateEvents);
        }

        return event;
    }

    @Override
    public List<Event> getEventsByDate(String eventDate, User user) {
        List<Event> events = userEventMapping.getOrDefault(user, new HashMap<>()).getOrDefault(eventDate, new ArrayList<>());
        return events.stream().filter(e -> e.getEventDate().equals(eventDate)).collect(Collectors.toList());
    }
}
