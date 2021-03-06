import java.util.ArrayList;
import java.util.Arrays;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

class TestClass {
    public static Reader scn = new Reader();
    public static int[] par;

    public static int findPar(int u) {
        return par[u] == u ? u : (par[u] = findPar(par[u]));
    }

    public static int mrPresident(int n, int[][] edges, long k) {
        par = new int[n + 1];
        for (int i = 0; i <= n; i++)
            par[i] = i;

        ArrayList<Integer> mstEdgeWeight = new ArrayList<>();
        long overallWeightSum = 0;
        for (int[] e : edges) {
            int p1 = findPar(e[0]);
            int p2 = findPar(e[1]);

            if (p1 != p2) {
                par[p1] = p2;
                mstEdgeWeight.add(e[2]);
                overallWeightSum += e[2];
                n--;
            }
        }

        if (n > 1)
            return -1;
        if (overallWeightSum <= k)
            return 0;

        int transform = 0;
        for (int i = mstEdgeWeight.size() - 1; i >= 0; i--) {
            overallWeightSum = overallWeightSum - mstEdgeWeight.get(i) + 1;
            transform++;

            if (overallWeightSum <= k)
                break;
        }

        return overallWeightSum <= k ? transform : -1;
    }

    public static void mrPresident() throws IOException {
        int n = scn.nextInt();
        int m = scn.nextInt();
        long k = scn.nextLong();

        int[][] edges = new int[m][3];
        for (int i = 0; i < m; i++) {
            edges[i] = new int[] { scn.nextInt(), scn.nextInt(), scn.nextInt() };
        }

        Arrays.sort(edges, (a, b) -> {
            return a[2] - b[2];
        });

        System.out.println(mrPresident(n, edges, k));

    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 28;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[255]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    if (cnt != 0) {
                        break;
                    } else {
                        continue;
                    }
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }

    public static void main(String args[]) throws IOException {
        mrPresident();
    }
}
