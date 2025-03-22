package lowleveldesign.googlecalender;

import lowleveldesign.googlecalender.model.Event;
import lowleveldesign.googlecalender.model.User;
import lowleveldesign.googlecalender.repositories.*;
import lowleveldesign.googlecalender.services.*;

import java.util.List;
import java.util.Scanner;

public class CalenderApplication {
    UserService userService;
    EventService eventService;

    CalenderApplication() {
        userService = new DefaultUserService(new InMemoryUserRepository());
        eventService = new DefaultEventService(new InMemoryEventRepository());
    }

    public static void main(String[] args) {
        CalenderApplication application = new CalenderApplication();
        User user1 = application.userService.createUser("user1@gamil.com");
        User user2 = application.userService.createUser("user2@gamil.com");
        User user3 = application.userService.createUser("user3@gmail.com");

        Event event1 = application.eventService.createEvent(
                "Global all hands",
                "04/02/2025",
                "22:30",
                "23:30",
                List.of(user1, user2, user3),
                user1
        );

        Event event2 = application.eventService.createEvent(
                "Daily scrum meeting",
                "04/02/2025",
                "10:30",
                "11:30",
                List.of(user2, user3),
                user2
        );


        List<Event> events = application.eventService.getEventsByDate("04/02/2025", user3);
        events.forEach(e -> System.out.println(e.toString()));

        Event event3 = application.eventService.updateEvent(event1, Event.builder().title("Global all hands")
                        .eventDate("05/02/2025")
                        .startTime("21:30")
                        .endTime("22:30")
                        .participants(List.of(user1, user2))
                        .createdBy(user1)
                .build());

        events = application.eventService.getEventsByDate("05/02/2025", user2);
        System.out.println("================================");
        events.forEach(e -> System.out.println(e.toString()));
    }
}
