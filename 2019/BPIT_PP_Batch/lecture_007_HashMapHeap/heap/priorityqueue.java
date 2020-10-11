import java.util.ArrayList;
public class priorityqueue{

    private ArrayList<Integer> pq;
    private boolean isMaxHeap = true;

    // Constructor.===========================================

    public priorityqueue(int[] arr,boolean isMax){
        intialize(isMax);
        for(int ele : arr) pq.add(ele);
        constructHeap();
    }

    public priorityqueue(){
        intialize(true);   
    }

    private void intialize(boolean isMax){
        pq = new ArrayList<>();
        isMaxHeap = isMax;
    }

    private void constructHeap(){
        for(int i = pq.size() - 1; i>=0 ;i--)
           downHeapify(i);
    }

    private int compareTo(int i,int j){
        if(isMaxHeap) return pq.get(i) - pq.get(j);
        else return pq.get(j) - pq.get(i);
    }

    private void swap(int i,int j){
        int v1 = pq.get(i);
        int v2 = pq.get(j);

        pq.set(i,v2);
        pq.set(j,v1);
    } 

    private void downHeapify(int pi){  // O(log(n))
        int maxidx = pi;
        int lci = (pi << 1) + 1;
        int rci = (pi << 1) + 2;

        if(lci < pq.size() && compareTo(lci,maxidx) > 0 ) maxidx = lci;
        if(rci < pq.size() && compareTo(rci,maxidx) > 0) maxidx = rci;

        if(maxidx != pi){
            swap(maxidx,pi);
            downHeapify(maxidx);
        }
    }

    private void upHeapify(int ci){  // O(log(n))
        int pi = (ci - 1) >> 1;
        if(pi >=0 && compareTo(ci,pi) > 0){
            swap(ci,pi);
            upHeapify(pi);
        }
    }

    //=====================================================================

    public int size(){
        return pq.size();
    }

    public boolean isEmpty(){
        return pq.size() == 0;
    }

    public void add(int val){ // O(log(n))
        pq.add(val);
        upHeapify(pq.size()-1);
    }

    public int peek() throws Exception{ // O(1)
        if(pq.size() == 0){
            throw new Exception("NullPointerException");
        }

        return pq.get(0);
    }

    public int poll() throws Exception{  // O(log(n))
        if(pq.size() == 0){
            throw new Exception("NullPointerException");
        }
        
        for(int ele : pq) System.out.print(ele + " ");
        System.out.println();
        
        int rv = pq.get(0);
        swap(0, pq.size() - 1);

        pq.remove(pq.size() - 1);
        downHeapify(0);

        return rv;
    }

    // public void update(int idx){
    //     downHeapify(idx);
    //     upHeapify(idx);
    // }   
}