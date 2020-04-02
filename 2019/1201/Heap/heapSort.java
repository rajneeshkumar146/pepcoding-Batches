import java.util.ArrayList;
public class heapSort{
    
    public static void downHeapify(int[] arr,int parIdx,int ei,boolean isMax){ //logn
        int maxidx=parIdx;
        int lci=2*parIdx+1;
        int rci=2*parIdx+2;

        if(lci<=ei && compareTo(arr,lci,maxidx,isMax)) maxidx=lci;
        if(rci<=ei&& compareTo(arr,rci,maxidx,isMax)) maxidx=rci;
        
        if(parIdx!=maxidx){
            swap(arr,maxidx,parIdx);
            downHeapify(arr,maxidx,ei,isMax);
        }
    }

    public static void swap(int[] arr,int a,int b){
        int temp=arr[a];
        arr[a]=arr[b];
        arr[b]=temp;
    }

    public static boolean compareTo(int[] arr,int ci,int pi,boolean isMax){
        if(isMax) return arr[ci]>arr[pi];
        else return arr[ci]<arr[pi];
    }

    public static void main(String[] args){
        int[] arr={6,8,2,-4,18,4,10,9,8,9,16,15,13,11,-9,-10};
        int ei=arr.length-1;
        boolean isMax=true;
        for(int i=ei;i>=0;i--){
            downHeapify(arr,i,ei,isMax);
        }

        while(ei!=0){
            swap(arr,0,ei);
            ei--;
            downHeapify(arr,0,ei,isMax);
        }

        for(int ele: arr) System.out.print(ele+" ");


     }



}
