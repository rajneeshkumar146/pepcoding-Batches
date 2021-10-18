import java.util.PriorityQueue;

public class heapQuestions {

    public static int kthSmallest(int[] arr, int l, int r, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });

        while (l <= r) {
            pq.add(arr[l]);
            if (pq.size() > k)
                pq.remove();
            l++;
        }

        return pq.peek();
    }

    // 378
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int i1 = a / m, j1 = a % m;
            int i2 = b / m, j2 = b % m;

            return matrix[i1][j1] - matrix[i2][j2];
        });

        for (int i = 0; i < n; i++)
            pq.add(i * m + 0);

        int r = 0, c = 0;
        while (k-- > 0) {
            int idx = pq.remove();
            r = idx / m;
            c = idx % m;
            if (c + 1 < m)
                pq.add(r * m + c + 1);
        }

        return matrix[r][c];
    }

    public int[][] kClosest(int[][] points, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int d1 = points[a][0] * points[a][0] + points[a][1] * points[a][1];
            int d2 = points[b][0] * points[b][0] + points[b][1] * points[b][1];

            return d2 - d1;
        });

        for (int i = 0; i < points.length; i++) {
            pq.add(i);
            if (pq.size() > k)
                pq.remove();
        }

        int[][] ans = new int[k][];
        int i = 0;
        while (pq.size() != 0) {
            int idx = pq.remove();
            ans[i++] = points[idx];
        }

        return ans;
    }

    // 692
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : words)
            map.put(s, map.getOrDefault(s, 0) + 1);

        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            if (map.get(a) == map.get(b))
                return b.compareTo(a);

            return map.get(a) - map.get(b);
        });

        for (String s : map.keySet()) {
            pq.add(s);
            if (pq.size() > k)
                pq.remove();
        }

        List<String> ans = new LinkedList<>();
        while (pq.size() != 0) {
            ans.add(0, pq.remove());
        }

        return ans;
    }

    public int swimInWater(int[][] grid) {
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        int n = grid.length, m = n;

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int i1 = a / m, j1 = a % m;
            int i2 = b / m, j2 = b % m;

            return grid[i1][j1] - grid[i2][j2];
        });

        boolean[][] vis = new boolean[n][m];
        pq.add(0);
        vis[0][0] = true;

        int minHeight = 0;
        while (pq.size() != 0) {
            int idx = pq.remove();
            int i = idx / m, j = idx % m;
            int height = grid[i][j];

            // time +=Math.max(0, height - minHeight);
            minHeight = Math.max(minHeight, height);

            if (i == n - 1 && j == m - 1)
                break;

            for (int[] d : dir) {
                int r = i + d[0];
                int c = j + d[1];

                if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
                    vis[r][c] = true;
                    pq.add(r * m + c);
                }
            }
        }

        return minHeight;
    }
}