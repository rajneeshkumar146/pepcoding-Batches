import java.util.*;

// Do not edit the class below except for the buildHeap,
// siftDown, siftUp, peek, remove, and insert methods.
// Feel free to add new properties and methods to the class.
class Program {
    static class MinHeap {
        private boolean isMaxHeap = false;
        List<Integer> heap = new ArrayList<Integer>();

        public MinHeap(List<Integer> array) {
            heap = buildHeap(array);
        }

        public List<Integer> buildHeap(List<Integer> data) {
            List<Integer> arr = new ArrayList<>();

            for (int ele : data)
                arr.add(ele);

            // O(NLogN) -> O(N)
            for (int i = arr.size() - 1; i >= 0; i--) {
                siftDown(i, arr.size() - 1, arr);
            }

            return arr;
        }

        private void swap(int i, int j, List<Integer> arr) {
            int eith = arr.get(i);
            int ejth = arr.get(j);

            arr.set(i, ejth);
            arr.set(j, eith);
        }

        private int compareTo(int i, int j, boolean isMaxHeap, List<Integer> arr) {
            if (isMaxHeap) {
                return arr.get(i) - arr.get(j);
            } else {
                return arr.get(j) - arr.get(i);
            }
        }

        public void siftDown(int pi, int ei, List<Integer> arr) {
            int lci = 2 * pi + 1;
            int rci = 2 * pi + 2;
            int maxIdx = pi;

            if (lci <= ei && compareTo(lci, maxIdx, isMaxHeap, arr) > 0)
                maxIdx = lci;
            if (rci <= ei && compareTo(rci, maxIdx, isMaxHeap, arr) > 0)
                maxIdx = rci;

            if (pi != maxIdx) {
                swap(pi, maxIdx, arr);
                siftDown(maxIdx, ei, arr);
            }
        }

        public void siftUp(int ci, List<Integer> arr) {
            int pi = (ci - 1) / 2;
            if (pi >= 0 && compareTo(ci, pi, isMaxHeap, arr) > 0) {
                swap(pi, ci, arr);
                siftUp(pi, arr);
            }
        }

        public int peek() {
            return this.heap.get(0);
        }

        public int remove() {
            int rele = this.heap.get(0);

            swap(0, this.heap.size() - 1, heap);
            this.heap.remove(this.heap.size() - 1);

            siftDown(0, this.heap.size() - 1, heap);

            return rele;
        }

        public void insert(int data) {
            this.heap.add(data);
            siftUp(this.heap.size() - 1, heap);
        }
    }
}
