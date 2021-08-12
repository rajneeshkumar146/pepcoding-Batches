import java.util.Arrays;

public class DSUQuestions {

    int[] par, size;

    public int findPar(int u) {
        return par[u] == u ? u : (par[u] = findPar(par[u]));
    }

    // 839
    public boolean isSimilar(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i) && ++count > 2)
                return false;
        }
        return true;
    }

    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        par = new int[n];
        for (int i = 0; i < n; i++)
            par[i] = i;

        int noOfSet = n;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    int p1 = findPar(i);
                    int p2 = findPar(j);

                    if (p1 != p2) {
                        par[p1] = p2;
                        noOfSet--;
                    }
                }
            }
        }

        return noOfSet;
    }

    int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    int n, m;

    public boolean countSubIslands_dfs(int[][] grid1, int[][] grid2, int i, int j) {

        grid2[i][j] = 0;
        boolean res = true;
        for (int[] d : dir) {
            int r = i + d[0];
            int c = j + d[1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid2[r][c] == 1) {
                res = countSubIslands_dfs(grid1, grid2, r, c) && res;
            }
        }

        return res && (grid1[i][j] == 1);
    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        n = grid1.length;
        m = grid1[0].length;

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid2[i][j] == 1) {
                    count += countSubIslands_dfs(grid1, grid2, i, j) ? 1 : 0;
                }
            }
        }

        return count;
    }

   

}