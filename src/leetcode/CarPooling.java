package leetcode;

import java.util.ArrayList;
import java.util.List;

class Stop {
    int index;
    int passenger;
    boolean isPickup;

    Stop(int index, int passenger, boolean isPickup) {
        this.index = index;
        this.passenger = passenger;
        this.isPickup = isPickup;
    }

    @Override
    public String toString() {
        return "[" + index + " "+ passenger + " " + isPickup + "]";
    }
}

public class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        List<Stop> stops = new ArrayList<>();
        for(int[] trip: trips) {
            stops.add(new Stop(trip[1], trip[0], true));
            stops.add(new Stop(trip[2], trip[0], false));
        }

        stops.sort((Stop a, Stop b) -> {
            if(a.index < b.index) {
                return -1;
            } else if(a.index == b.index) {
                if(a.isPickup && !b.isPickup) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return 1;
            }
        });

        int empty = capacity;
        for(Stop stop: stops) {
            if(stop.isPickup) {
                empty -= stop.passenger;
            } else {
                empty += stop.passenger;
            }

            if(empty < 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        CarPooling obj = new CarPooling();
        System.out.println(obj.carPooling(new int[][]{{2,1,5}, {3,3,7}}, 4));
    }
}
