package leetcode;


import java.util.*;

class KElementsWithMaxSum {
    public long[] findMaxSum(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        long[] answer = new long[n];

        // Create a list of pairs (nums1[i], nums2[i], original index)
        List<int[]> pairs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            pairs.add(new int[]{nums1[i], nums2[i], i});
        }

        // Sort the pairs based on nums1[i] in ascending order
        Collections.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));

        // Min-heap to keep track of the top k nums2[j] values
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Iterate through the sorted pairs
        for (int i = 0; i < n; i++) {
            int nums1Val = pairs.get(i)[0];
            int nums2Val = pairs.get(i)[1];
            int originalIndex = pairs.get(i)[2];

            // Add nums2Val to the heap
            minHeap.offer(nums2Val);

            // If the heap size exceeds k, remove the smallest element
            if (minHeap.size() > k) {
                minHeap.poll();
            }

            // Calculate the sum of the top k elements in the heap
            long sum = 0;
            for (int val : minHeap) {
                sum += val;
            }

            // Store the result in the answer array at the original index
            answer[originalIndex] = sum;
        }

        return answer;
    }

    public static void main(String[] args) {
        KElementsWithMaxSum solution = new KElementsWithMaxSum();
        int[] nums1 = {4, 2, 1, 5, 3};
        int[] nums2 = {10, 20, 30, 40, 50};
        int k = 2;
        long[] result = solution.findMaxSum(nums1, nums2, k);
        System.out.println(Arrays.toString(result)); // Expected Output: [80, 30, 0, 80, 50]
    }
}
