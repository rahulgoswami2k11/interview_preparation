package leetcode;

import java.util.ArrayList;
import java.util.List;

class MergeTripletSolution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        List<int[]> filtered = new ArrayList<>();
        for(int[] arr: triplets) {
            if(arr[0] > target[0] || arr[1] > target[1] || arr[2] > target[2]) {
                continue;
            } else {
                filtered.add(arr);
            }
        }

        boolean first = false;
        boolean second = false;
        boolean third = false;

        for(int[] arr: filtered) {
            if(arr[0] == target[0]) {
                first = true;
            }

            if(arr[1] == target[1]) {
                second = true;
            }

            if(arr[2] == target[2]) {
                third = true;
            }
        }

        return first && second && third;
    }
}

public class MergeTriplets {
    public static void main(String[] args) {

    }
}
