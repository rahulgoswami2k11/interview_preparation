package interview;

import java.util.Arrays;

public class NexthinkInterview {

    char[][] canvas;
    boolean[][] visited;
    int m;
    int n;
    public NexthinkInterview(int m, int n) {
        this.m = m;
        this.n = n;
        canvas = new char[][]{
                {'1', '1', '3'},
                {'1', '1', '4'},
                {'1', '1', '4'}
        };
        visited = new boolean[m][n];
    }

    public char[][] fillColor(char newColor, int row, int col) {
        dfs(newColor, row, col);
        return canvas;
    }

    public void dfs(char newColor, int row, int col) {
        canvas[row][col] = newColor;
        visited[row][col] = true;

        int[] X = new int[]{1, -1, 0, 0};
        int[] Y = new int[]{0, 0, 1, -1};

        for(int k=0; k<4; k++) {
            int ii = row + X[k];
            int jj = col + Y[k];

            if(ii >= 0 && ii < m && jj >= 0 && jj < n) {
                if(!visited[ii][jj] && canvas[ii][jj] == '1') {
                    dfs(newColor, ii, jj);
                }
            }
        }
    }


    public static void main(String[] args) {
        NexthinkInterview interview = new NexthinkInterview(3, 3);
        char[][] result = interview.fillColor('2', 0, 0);

        for (char[] chars : result) {
            System.out.println(Arrays.toString(chars));
        }

    }
}
