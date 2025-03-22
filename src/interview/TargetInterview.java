package interview;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.*;

class TargetInterview {

    public static boolean isValid(String s) {
        Stack<Character> st = new Stack<>();

        for(char c: s.toCharArray()) {
            if(c == '[' || c == '{' || c == '(') {
                st.push(c);
            } else if(c == ']') {
                if(st.peek() != '[') {
                    return false;
                }
                st.pop();
            } else if(c == '}') {
                if(st.peek() != '{') {
                    return false;
                }
                st.pop();
            } else if(c == ')') {
                if(st.peek() != '(') {
                    return false;
                }
                st.pop();
            }
        }

        return st.isEmpty();
    }

    public static void main(String[] args) {
        String s = "[]{}()";
        System.out.println(TargetInterview.isValid(s));
    }
}

@Data
@AllArgsConstructor
@Builder
class User {
    String userId;
}

@Data
@AllArgsConstructor
@Builder
class Meeting {
    String date;
    User owner;
    long startTime;
    long endTime;
    List<User> participants;
}

@Data
@AllArgsConstructor
@Builder
class UserCalender {
    User owner;
    Map<String, List<Meeting>> userDailyMeetingMap;

    public UserCalender(User user) {
        this.owner = user;
        this.userDailyMeetingMap = new HashMap<>();
    }

    public boolean addMeeting(Meeting meeting) {
        String date = meeting.getDate();
        List<Meeting> dateMeetings = userDailyMeetingMap.getOrDefault(date, new ArrayList<>());

        for(Meeting existingMeeting : dateMeetings) {
            if(existingMeeting.getEndTime() > meeting.getStartTime() &&
                    existingMeeting.getStartTime() < meeting.getEndTime()) {
                return false;
            }
        }

        dateMeetings.add(meeting);
        userDailyMeetingMap.put(date, dateMeetings);
        return true;
    }
}

@Data
@AllArgsConstructor
@Builder
class UsersCalender {
    Map<User, UserCalender> usersCalenderMap;

    public UsersMeetingResponse addMeeting(Meeting meeting) {
        List<User> participants = meeting.getParticipants();
        UsersMeetingResponse response = UsersMeetingResponse.builder()
                .userMeetingResponseList(new ArrayList<>())
                .build();
        for(User participant: participants) {
            UserCalender userCalender = usersCalenderMap.get(participant);
            boolean isAdded = userCalender.addMeeting(meeting);
            response.userMeetingResponseList.add(
                    UserMeetingResponse.builder()
                            .user(participant)
                            .accepted(isAdded)
                            .build()
            );
        }

        return response;
    }
}

@Data
@AllArgsConstructor
@Builder
class UserMeetingResponse{
    User user;
    boolean accepted;
}

@Data
@AllArgsConstructor
@Builder
class UsersMeetingResponse {
    List<UserMeetingResponse> userMeetingResponseList;
}

class CalenderDemo {
    public static void main(String[] args) {
        User user1 = User.builder().userId("user1").build();
        User user2 = User.builder().userId("user2").build();
        User user3 = User.builder().userId("user3").build();
        User user4 = User.builder().userId("user4").build();

        Meeting meeting = Meeting.builder()
                .startTime(11)
                .endTime(12)
                .participants(List.of(user1, user2, user3, user4))
                .owner(user1)
                .build();

        UserCalender user1Calender = new UserCalender(user1);
        UserCalender user2Calender = new UserCalender(user2);
        UserCalender user3Calender = new UserCalender(user3);
        UserCalender user4Calender = new UserCalender(user4);

        UsersCalender usersCalender = UsersCalender.builder()
                .build();
    }
}




