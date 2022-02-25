import java.io.*;
import java.util.*;

public class Main {

     public static int goldMine(int[][] gold) {
        int[][] dir = { { -1, 1 }, { 0, 1 }, { 1, 1 } };
        int N = gold.length, M = gold[0].length;
        int[][] dp = new int[N][M];

        for (int sc = M - 1; sc >= 0; sc--) {
             for (int sr = N - 1; sr >= 0; sr--) {
                if (sc == M - 1) {
                    dp[sr][sc] = gold[sr][sc];
                    continue;
                }

                int maxGold = 0;
                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r < N && c < M) {
                        maxGold = Math.max(maxGold, dp[r][c] + gold[sr][sc]);
                    }
                }
                dp[sr][sc] = maxGold;
            }
        }

        int maxGold = 0;
        for (int r = 0; r < N; r++)
            maxGold = Math.max(maxGold, dp[r][0]);

        return maxGold;
    }

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      int m = Integer.parseInt(br.readLine());
      int[][] arr = new int[n][m];

      for (int i = 0; i < n; i++) {
         String str = br.readLine();
         for (int j = 0; j < m; j++) {
            arr[i][j] = Integer.parseInt(str.split(" ")[j]);
         }
      }

      System.out.println(goldMine(arr));
    }

}