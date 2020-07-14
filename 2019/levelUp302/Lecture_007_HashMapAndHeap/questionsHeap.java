import java.util.PriorityQueue;

public class questionsHeap {

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });

        for (int i = 0; i < n; i++)
            pq.add(new int[] { matrix[i][0], i, 0 });

        while (k-- > 1) {
            int[] rvtx = pq.remove();
            if (++rvtx[2] < m)
                pq.add(new int[] { matrix[rvtx[1]][rvtx[2]], rvtx[1], rvtx[2] });

        }

        return pq.remove()[0];
    }

    public int[] kthSmallestPrimeFraction(int[] A, int k) {
        int n = A.length;

        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> {
            return (int) (a[0] - b[0]);
        });

        for (int i = 1; i < n; i++) {
            pq.add(new double[] { A[0] / A[i], 0, i });
        }

        while (k-- > 1) {
            double[] rvtx = pq.remove();
            if (rvtx[1] + 1 < rvtx[2]) {

                int x = ((int) rvtx[1]) + 1;
                int y = (int) rvtx[2];
                double val = A[x] / A[y];

                pq.add(new double[] { val, x, y });
            }
        }

        double[] rvtx = pq.remove();
        int x = (int) rvtx[1];
        int y = (int) rvtx[2];
        return new int[] { x, y };
    }

    public int[] kthSmallestPrimeFraction2(int[] A, int k) {
        int n = A.length;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return A[a[0]] * A[b[1]] - A[b[0]] * A[a[1]];
        });

        for (int i = 1; i < n; i++) {
            pq.add(new int[] { 0, i });
        }

        while (k-- > 1) {
            int[] rvtx = pq.remove();
            if (++rvtx[0] < rvtx[1])
                pq.add(new int[] { rvtx[0], rvtx[1] });
        }

        int[] rvtx = pq.remove();
        return new int[] { rvtx[0], rvtx[1] };
    }

}