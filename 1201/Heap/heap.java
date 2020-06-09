import java.util.ArrayList;
public class heap{
    public static ArrayList<Integer> arr=new ArrayList<>();
    public static boolean isMax_=false;

    public static void createHeap(int[] dataSet,boolean isMax){
        for(int ele:dataSet) arr.add(ele);
        isMax_=isMax;
        
        int ei=arr.size()-1;
        for(int i=ei;i>=0;i--){
            downHeapify(i,ei);
        }  
    }

    public static void add(int data){
        arr.add(data);
        uphepify(arr.size()-1);
    }

    public static int remove(){
        int rv=arr.get(0);
        
        swap(0,arr.size()-1);
        arr.remove(arr.size()-1);
        downHeapify(0,arr.size()-1);
        
        return rv;
    }

    public static int top(){
        return arr.get(0);
    }

    public static void downHeapify(int parIdx,int ei){ //logn
        int maxidx=parIdx;
        int lci=2*parIdx+1;
        int rci=2*parIdx+2;

        if(lci<=ei && compareTo(lci,maxidx)) maxidx=lci;
        if(rci<=ei && compareTo(lci,maxidx)) maxidx=rci;
        
        if(parIdx!=maxidx){
            swap(parIdx,maxidx);
            downHeapify(maxidx,ei);
        }
    }

    public static void uphepify(int ci){   // logn
        int pi=(ci-1)/2;
        if(pi>=0 && compareTo(ci,pi)){
            swap(ci,pi);
            uphepify(pi);
        }
    }

    public static void swap(int a,int b){
        int val1=arr.get(a);
        int val2=arr.get(b);

        arr.set(a,val2);
        arr.set(b,val1);
    }

    public static boolean compareTo(int ci,int pi){
        if(isMax) return arr.get(ci)>arr.get(pi);
        else return arr.get(ci)<arr.get(pi);
    }



    public static void main(String[] args){
       int[] dataSet={6,8,2,-4,18,4,10,9,8,9,16,15,13,11,-9,-10};
       createHeap(dataSet,false);
       int ei=dataSet.length-1;
       
       while(arr.size()!=0){
           int a=remove();
           dataSet[ei]=a;
           ei--;
       }

       for(int ele: dataSet) System.out.print(ele+ " ");
    }
}