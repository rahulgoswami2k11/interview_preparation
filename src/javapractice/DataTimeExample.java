package javapractice;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataTimeExample {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();

        LocalDate startOfWeek = today.with(DayOfWeek.SUNDAY);
        if(startOfWeek.isAfter(today)) {
            startOfWeek = startOfWeek.minusWeeks(1);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startOfWeekString = startOfWeek.format(formatter);

        // Print the result
        System.out.println("Start of the week: " + startOfWeekString);
    }
}
