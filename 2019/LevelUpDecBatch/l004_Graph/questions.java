import java.util.LinkedList;

public class questions {

    // 994
    public int orangesRotting(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
        int time = 0, freshOranges = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2)
                    que.addLast(i * m + j);
                else if (grid[i][j] == 1)
                    freshOranges++;
            }
        }

        if (freshOranges == 0)
            return 0;
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int idx = que.removeFirst();
                int r = idx / m;
                int c = idx % m;

                for (int d = 0; d < 4; d++) {
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];
                    if (x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 1) {
                        freshOranges--;
                        grid[x][y] = 2;
                        que.addLast(x * m + y);
                        if (freshOranges == 0)
                            return time + 1;
                    }
                }
            }

            time++;
        }

        return -1;
    }

    int[] par;

    int findPar(int u) {
        return par[u] == -1 ? u : (par[u] = findPar(par[u]));
    }

    public int[] findRedundantConnection(int[][] edges) {
        int N = edges.length + 1;
        par = new int[N];
        Arrays.fill(par, -1);

        for (int[] edge : edges) {
            int p1 = findPar(edge[0]);
            int p2 = findPar(edge[1]);

            if (p1 != p2)
                par[p1] = p2;
            else
                return edge;

        }

        return new int[0];

    }

    public boolean isSimilar(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i) && ++count > 2)
                return false;
        }

        return true;

    }

    public int numSimilarGroups(String[] strs) {
        int count = strs.length;
        int n = strs.length;

        par = new int[n];
        for (int i = 0; i < n; i++)
            par[i] = i;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    int p1 = findPar(i);
                    int p2 = findPar(j);

                    if (p1 != p2) {
                        par[p1] = p2;
                        count--;
                    }
                }

            }

        }

        return count;
    }

}