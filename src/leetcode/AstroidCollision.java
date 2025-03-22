package leetcode;

import java.util.Arrays;
import java.util.Stack;

public class AstroidCollision {
    public int[] asteroidCollision(int[] arr) {
        Stack<Integer> st = new Stack<>();
        for(int astroid: arr) {
            if(st.isEmpty() || astroid > 0 || (st.peek() < 0 && astroid < 0)) {
                st.add(astroid);
            } else {
                while(!st.isEmpty() && st.peek() > 0) {
                    int top = st.pop();
                    int remainAfterCollision = top + astroid;

                    if(remainAfterCollision > 0) {
                        st.push(top);
                        break;
                    } else if(remainAfterCollision == 0) {
                        astroid = 0;
                        break;
                    }
                }

                if(astroid != 0) {
                    st.push(astroid);
                }
            }
        }

        int[] res = new int[st.size()];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = st.pop();
        }

        return res;
    }

    public static void main(String[] args) {
        AstroidCollision obj = new AstroidCollision();
        System.out.println(Arrays.toString(obj.asteroidCollision(new int[]{-2,-2,1,-2})));
    }
}
