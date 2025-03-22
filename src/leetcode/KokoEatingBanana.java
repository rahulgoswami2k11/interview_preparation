package leetcode;

import java.util.stream.IntStream;

public class KokoEatingBanana {

    private boolean canEatAll(int[] piles, int curr, int h) {
        int count = 0;

        for(int val: piles) {
            count += (int)Math.ceil((double) val / curr);
        }

        return count <= h;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;
        int max = IntStream.of(piles).max().getAsInt();
        int min = 1;

        int res = max;
        while(min <= max) {
            int curr = min + (max - min) / 2;
            if(canEatAll(piles, curr, h)) {
                res = Math.min(res, curr);
                max = curr-1;
            } else {
                min = curr+1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        KokoEatingBanana solution = new KokoEatingBanana();
        System.out.println(solution.minEatingSpeed(new int[]{805306368,805306368,805306368}, 1000000000));
    }
}
