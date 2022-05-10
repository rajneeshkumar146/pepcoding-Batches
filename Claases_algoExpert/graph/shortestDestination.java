import java.util.*;

class solution {
    private boolean validateLocation(int r, int c, int n, int m) {
        return r >= 0 && c >= 0 && r < n && c < m;
    }

    public int floodFill_BFS(int dr, int dc, int[][] vis, int[][] dir) {
        int count = 0, n = vis.length, m = vis[0].length;

        // {r,c}
        LinkedList<int[]> que = new LinkedList<>(); // addLast, removeFirst
        que.addLast(new int[] { 0, 0 });
        vis[0][0] = 0;
        int level = 0;

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int[] loc = que.removeFirst();

                if (loc[0] == dr && loc[1] == dc)
                    return level;
                for (int[] d : dir) {
                    int r = loc[0] + d[0];
                    int c = loc[1] + d[1];

                    if (validateLocation(r, c, n, m) && vis[r][c] == 1) {
                        vis[r][c] = 0;
                        que.addLast(new int[] { r, c });
                    }
                }
            }
            level++;
        }

        return -1;
    }

    int shortestDistance(int N, int M, int arr[][], int X, int Y) {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        if (arr[0][0] == 0 || arr[X][Y] == 0)
            return -1;

        return floodFill_BFS(X, Y, arr, dir);
    }
}