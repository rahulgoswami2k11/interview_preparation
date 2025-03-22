package javapractice;

public class MaxSum {

    private static long maxSum(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];

        dp[n-1][0] = nums[n-1];
        dp[n-1][1] = 0;

        for(int i=n-2; i>=0; i--) {
            dp[i][0] = Math.max(nums[i] + dp[i+1][1], dp[i+1][0]);
            dp[i][1] = Math.max(dp[i+1][1], dp[i+1][0] - nums[i]);
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,6,7,8};
        System.out.println(maxSum(nums));
    }
}
