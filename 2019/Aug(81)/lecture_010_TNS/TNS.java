import java.util.Arrays;
public class TNS{
    public static void main(String[] args){
    //    int[] arr1={1,23,24,34};
    //    int[] arr2={1,2,3,12,12,15,17,19,20,22,24,43};

        // int[] arr={1,23,133,11,0,3,-1,-4,4,5,22,232,56,34,54,1};
        // display(mergerSort(arr,0,arr.length-1));
    
        sieve(10000000);

    }

    public static void display(int[] arr){
        for(int ele: arr){
            System.out.print(ele + " ");
        }
        System.out.println();        
    }

    public static int[] mergerSort(int[] arr,int li,int ri){
        if(li==ri){
            int[] base=new int[1];
            base[0]=arr[li];
            return base;
        }

        int mid=(li+ri)>>>1;
        int[] left=mergerSort(arr,li,mid);
        int[] right=mergerSort(arr,mid+1,ri);
        return mergerArrays(left,right);

    }

    public static int[] mergerArrays(int[] arr1,int [] arr2){
       int[] arr=new int[arr1.length + arr2.length];
       int i=0,j=0,k=0;
       while(i<arr1.length || j<arr2.length){
           if(i<arr1.length && j<arr2.length){
             
            if(arr1[i]<arr2[j]) 
               arr[k++]=arr1[i++];
            else 
              arr[k++]=arr2[j++];
           
            }else if(i<arr1.length){
             arr[k++]=arr1[i++];
           }else if(j<arr2.length){
             arr[k++]=arr2[j++];
           }
       }

       return arr;

    }
    
    public static void sieve(int n){
        boolean[] arr=new boolean[n+1];
        Arrays.fill(arr,true);
        arr[0]=arr[1]=false;

        for(int i=2;i*i<=n;i++){
            if(!arr[i]) continue;
            for(int j=i*i;j<=n;j+=i){
              arr[j]=false;
            }
        }

        for(int i=0;i<=n;i++){
            if(arr[i]) System.out.println(i);
        }

    }

}