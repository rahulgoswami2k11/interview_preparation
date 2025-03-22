package lowleveldesign.googlecalender.model;

import lombok.*;

import java.util.List;

@Data
@Builder
public class Event {
    private String title;
    private String eventDate;
    private String startTime;
    private String endTime;
    private List<User> participants;
    private User createdBy;

}
