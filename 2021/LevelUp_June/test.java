import java.util.*;

public class test {
    public int Mintime(int grid[][], boolean vis[][], List<int[]> rot, int fresh) {
        LinkedList<Integer> q = new LinkedList<>();

        int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        int m = grid[0].length;
        // int idx=r*m+c;
        for (int i = 0; i < rot.size(); i++) {
            int idx = rot.get(i)[0] * m + rot.get(i)[1];
            q.addLast(idx);
        }

        int time = 0;
        while (q.size() != 0) {
            int size = q.size();

            while (size > 0) {

                int idx = q.removeFirst();

                int r = idx / m;
                int c = idx % m;

                if (vis[r][c] == true) {
                    continue;
                }

                vis[r][c] = true;

                if (grid[r][c] == 1) {
                    fresh--;
                }

                if (fresh == 0)
                    return time;

                for (int i = 0; i < dir.length; i++) {
                    int nr = r + dir[i][0];
                    int nc = c + dir[i][1];

                    if (nr >= 0 && nc >= 0 && nr < grid.length && nc < grid[0].length && grid[nr][nc] == 1
                            && !vis[nr][nc]) {
                        q.addLast(nr * m + nc);
                    }
                }
                size--;
            }

            time++;
        }

        return fresh == 0 ? time - 1 : -1;
    }

    public int orangesRotting(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        LinkedList<Integer> q = new LinkedList<>();

        List<int[]> rot = new ArrayList<>();
        int fresh = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    rot.add(new int[] { i, j });
                }
                if (grid[i][j] == 1)
                    fresh++;
            }
        }

        // System.out.print(fresh);

        boolean vis[][] = new boolean[m][n];

        return Mintime(grid, vis, rot, fresh);

    }
}