public class TNS{
    public static void main(String[] args){
        // int[] arr1={3,7,1,654,2};
        // int[] arr2={1,2,3,10,26,1,4,1,4,6,3,22,1,11,2,44,67};
        // int[] res=mergerSortedArray(arr1,arr2);
        
        // display(mergeSort(arr2,0,arr2.length-1));
        // pivot(arr2,0,arr2.length-1,10);
        

        int[] arr={0,1,1,1,1,0,0,0,0,2,2,2,0,2,0,0,1,2,1,1,2,1,2,1,2};
        sort0_1_2(arr);
    }

    public static void display(int[] arr){
        for(int ele: arr){
            System.out.print(ele + " ");
        }
        System.out.println();
    } 

    public static int[] mergeSort(int[] arr,int li,int ri){
       if(li==ri){
           int[] base=new int[1];
           base[0]=arr[li];
           return base;
       }

       int mid=(li+ri)>>>1;

       int[] left=mergeSort(arr,li,mid);
       int[] right=mergeSort(arr,mid+1,ri);
       
       return mergerSortedArray(left,right);
    }

    public static int[] mergerSortedArray(int[] arr1,int[] arr2){
          int[] arr=new int[arr1.length + arr2.length];
          int i=0;
          int j=0;
          int k=0;
        while(i<arr1.length || j<arr2.length){
            if(i<arr1.length && j<arr2.length){
                if(arr1[i]<arr2[j]){
                    arr[k]=arr1[i];
                    i++;
                }else{
                    arr[k]=arr2[j];
                    j++;
                }
            }else if(i<arr1.length){
                arr[k]=arr1[i];
                i++;
            }else if(j<arr2.length){
                arr[k]=arr2[j];
                j++;
            }
            k++;
        }

        return arr;
    }
    public static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public static void sort0_1(int[] arr){
        int pt=0;
        int itr=0;
        while(itr<arr.length){
            if(arr[itr]==0){
                swap(arr,pt,itr);
                pt++;
            }
            itr++;

        }
    }


    public static void pivot(int[] arr,int li,int ri,int pivot){
        int pt=li;
        int itr=li;
        while(itr<=ri){
            if(arr[itr] <= pivot){
                swap(arr,pt,itr);
                pt++;
            }
            itr++;
        }

        display(arr);
        System.out.println(pt);
    }

    public static void sort0_1_2(int[] arr){
        int pt=0;
        int pt1=arr.length-1;
        int itr=0;
        while(itr<=pt1){
            if(arr[itr]==0){
                 swap(arr,pt,itr);
                pt++;        
            }else if(arr[itr]==2){
                swap(arr,pt1,itr);
                pt1--;
                continue;
            }

            itr++;
            

        }

        display(arr);

    }
}