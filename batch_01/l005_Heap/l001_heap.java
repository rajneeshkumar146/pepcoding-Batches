import java.util.ArrayList;

public class l001_heap {
    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13 };
        priorityQueue pq = new priorityQueue(arr, false);

        
        while (pq.size() != 0) {
            System.out.print(pq.remove() + " ");
        }
    }

    public static class priorityQueue {
        ArrayList<Integer> arr = new ArrayList<>();
        boolean isMax = true;

        priorityQueue(boolean isMax) {
            this.isMax = isMax;
        }

        priorityQueue(int[] arr1, boolean isMax) {
            this.isMax = isMax;
            for (int i = 0; i < arr1.length; i++) {
                arr.add(arr1[i]);
            }

            for (int i = arr.size() - 1; i >= 0; i--) {
                downHeapify(i, arr.size() - 1);
            }
        }

        public void add(int val) {
            arr.add(val);
            upheapify(arr.size() - 1);
        }

        public int top() {
            return arr.get(0);
        }

        public int remove() { // top element.
            swap(0, arr.size() - 1);

            int remove_ele = arr.get(arr.size() - 1);
            arr.remove(arr.size() - 1);
            downHeapify(0, arr.size() - 1);

            return remove_ele;
        }

        public void update(int prevVal, int newVal) {
            int idx = -1;
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i) == prevVal) {
                    idx = i;
                    break;
                }
            }

            if (idx == -1)
                return;

            arr.set(idx, newVal);
            upheapify(idx);
            downHeapify(idx, arr.size() - 1);
        }

        public int size() {
            return arr.size();
        }

        public void upheapify(int ci) {
            int pi = (ci - 1) / 2;// (ci-1)>>1;
            if (pi >= 0 && compareTo(arr.get(ci), arr.get(pi)) > 0) {
                swap(ci, pi);
                upheapify(pi);
            }
        }

        public void downHeapify(int idx, int n) {
            int maxidx = idx;
            int lci = 2 * idx + 1;
            int rci = 2 * idx + 2;

            if (lci <= n && compareTo(arr.get(lci), arr.get(maxidx)) > 0) {
                maxidx = lci;
            }

            if (rci <= n && compareTo(arr.get(rci), arr.get(maxidx)) > 0) {
                maxidx = rci;
            }

            if (maxidx != idx) {
                swap(maxidx, idx);
                downHeapify(maxidx, n);
            }
        }

        public int compareTo(int val1, int val2) {
            if (isMax) {
                return (val1 - val2);
            } else {
                return val2 - val1;
            }
        }

        public void swap(int x, int y) {
            Integer val1 = arr.get(x);
            Integer val2 = arr.get(y);

            arr.set(x, val2);
            arr.set(y, val1);
        }

    }

}