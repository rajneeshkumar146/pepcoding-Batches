public class heapSort{
    public static void main(String[] args){

        int[] arr={10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13};
        heapSort(arr,false);
        for(int ele: arr) System.out.print(ele + " ");

    }


    public static boolean compareTo_(int a,int b, boolean isMax){
        if(isMax)
            return a > b;
        else 
            return a < b;
    }

    public static void swap(int[] arr,int a,int b){
        int temp = arr[a];
        arr[a]=arr[b];
        arr[b]=temp;
    }

    public static void downHeapify(int[] arr,int pi,boolean isMax,int lidx){
        int lci = (pi<<1) + 1;
        int rci = (pi<<1) + 2;
        int maxi=pi;

        if(lci<=lidx && compareTo_(arr[lci],arr[maxi],isMax)) maxi = lci;
        if(rci<=lidx && compareTo_(arr[rci],arr[maxi],isMax)) maxi = rci;
        
        if(maxi != pi){
            swap(arr,pi,maxi);
            downHeapify(arr,maxi,isMax,lidx);
        }
    }

    public static void heapSort(int[] arr,boolean isMax){
        int n=arr.length;
        for(int i=n-1;i>=0;i--){
            downHeapify(arr,i,isMax,n-1);
        }

        int lidx = n-1;
        while(lidx!=0){
            swap(arr,0,lidx);
            downHeapify(arr,0,isMax,--lidx);
        }
    }
}