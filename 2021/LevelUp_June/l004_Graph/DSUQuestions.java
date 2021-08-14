import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

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

    public int mrPresident() {
        Scanner scn = new Scanner(System.in);
        int N = scn.nextInt();
        int M = scn.nextInt();
        long K = scn.nextLong();

        ArrayList<int[]> Edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int u = scn.nextInt(), v = scn.nextInt(), w = scn.nextInt();
            Edges.add(new int[] { u, v, w });
        }

        Collections.sort(Edges, (a, b) -> {
            return a[2] - b[2];
        });

        par = new int[N + 1];
        for (int i = 0; i <= N; i++)
            par[i] = i;

        long totalCost = 0;
        int conversions = 0;
        int components = N;
        ArrayList<Integer> costOfRoad = new ArrayList<>();
        for (int[] e : Edges) {
            int u = e[0], v = e[1], w = e[2];
            int p1 = findPar(u), p2 = findPar(v);

            if (p1 != p2) {
                par[p1] = p2;
                totalCost += w;
                costOfRoad.add(w);
                components--;
            }
        }

        if (components > 1)
            return -1;

        for (int i = costOfRoad.size() - 1; i >= 0; i--) {
            if (totalCost > K) {
                totalCost = totalCost - costOfRoad.get(i) + 1;
                conversions++;
            } else {
                break;
            }
        }

        return totalCost > K ? -1 : conversions;
    }

}