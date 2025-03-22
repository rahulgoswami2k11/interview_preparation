package javapractice;

import java.util.ArrayList;
import java.util.List;

public class StringGenerator {

    private int calculate(String s) {
        int len = s.length();
        int[] arr = new int[len];

        int count = 0;
        for(int i=len-1; i>=0; i--) {
            if(s.charAt(i) == 'B') {
                count++;
            }
            arr[i] = count;
        }

        int res = 0;
        for(int i=0; i<len; i++) {
            if(s.charAt(i) == 'A') {
                res += arr[i];
            }
        }

        return res;
    }

    private void generateString(char[] arr, int index, int end, List<String> result) {
        if(index == end) {
            result.add(new String(arr));
            return;
        }

        arr[index] = 'A';
        generateString(arr, index+1, end, result);

        arr[index] = 'B';
        generateString(arr, index+1, end, result);
    }

    public String generate(int n, int len) {
        char[] arr = new char[len];
        List<String> strings = new ArrayList<>();

        generateString(arr, 0, len, strings);

        for(String value: strings) {
            if(calculate(value) == n) {
                return value;
            }
        }

        return null;

    }

    public String generateEfficient(int n, int len) {
        int k = 0;
        while(k * (len - k) < n) {
            k++;
        }

        if(k > len) {
            return null;
        }

        char[] res = new char[len];
        for(int i=0; i<k; i++) {
            res[i] = 'A';
        }

        for(int i=k; i<len; i++) {
            res[i] = 'B';
        }

        int excess = k * (len - k) - n;

        int i=k-1, j=k;
        while(i< len && j >= 0 && excess > 0) {
            res[i] = 'B';
            res[j] = 'A';
            excess--;
            i++;
            j--;
        }

        if(excess > 0) {
            return null;
        }

        return new String(res);
    }

    public static void main(String[] args) {
        StringGenerator solution = new StringGenerator();
        System.out.println(solution.generateEfficient(5, 5));
    }
}
