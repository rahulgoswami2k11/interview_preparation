package leetcode;

import java.util.Arrays;

class GasStationSolution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int gasSum = Arrays.stream(gas).reduce(0, Integer::sum);
        int costSum = Arrays.stream(cost).reduce(0, Integer::sum);


        if(gasSum < costSum) {
            return -1;
        }

        int start = 0;
        int remGas = 0;
        for(int i=0; i<gas.length; i++) {
            remGas += (gas[i] - cost[i]);

            if(remGas < 0) {
                start = i+1;
                remGas = 0;
            }
        }

        return start;
    }
}

public class GasStation {
}
