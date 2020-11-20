import java.util.ArrayList;
import java.util.Collections;
public class heap{

    public static class ownHeap{
        ArrayList<Integer> arr=new ArrayList<>();
        boolean isMaxHeap = false;

        public ownHeap(){

        }

        public ownHeap(int[] data){
            constructHeap(data);
        }

        public ownHeap(int[] data, boolean isMaxHeap){
            this.isMaxHeap = isMaxHeap;
            constructHeap(data);
        }

        private void constructHeap(int[] data){
            for(int d : data) arr.add(d);

            for(int i=arr.size()-1;i>=0;i--){
                downHeapify(i);
            }
        }

        public int compareTo(int a ,int b){
            if(isMaxHeap) return arr.get(a) - arr.get(b);
            else return arr.get(b) - arr.get(a);
        }

        private void downHeapify(int pi){ //O(logn)
            int maxIdx = pi;
            int lci = 2 * pi + 1;
            int rci = 2 * pi + 2;

            if(lci < arr.size() && compareTo(lci,maxIdx) > 0) maxIdx = lci;
            if(rci < arr.size() && compareTo(rci,maxIdx) > 0) maxIdx = rci;

            if(maxIdx != pi){
                swap(pi,maxIdx);
                downHeapify(maxIdx);
            }
        }

        private void upHeapify(int ci){ //O(logn)
            int pi = (ci - 1) / 2;

            if(pi >= 0 && compareTo(ci,pi) > 0){
                swap(pi,ci);
                upHeapify(pi);
            }
        }

        private void swap(int i,int j){
            int v1 = arr.get(i);
            int v2 = arr.get(j);

            arr.set(i,v2);
            arr.set(j,v1);
        }

        //public functions.===============================================

        public int size(){
            return this.arr.size();
        }

        public boolean isEmpty(){
            return size() == 0;
        }

        public void add(int data){ //O(logn)
            arr.add(data);
            upHeapify(this.arr.size() - 1);
        }

        public int poll(){  // O(logn) 
            for(int d: arr) System.out.print(d +  " ");

            int rv = this.arr.get(0);
            
            swap(0,arr.size()-1);
            this.arr.remove(arr.size()-1);

            downHeapify(0);
            return rv;
        }

        public int peek(){    // O(1)
            return this.arr.get(0);
        }
    }


    public static void main(String[] args){
        int[] arr2 = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13 };
        ownHeap hp = new ownHeap(arr2, true);

        ArrayList<Integer> ans = new ArrayList<>();
        while(hp.size()!=0){
            ans.add(hp.poll());
            System.out.println();
        }

        System.out.println(ans);
    }

}