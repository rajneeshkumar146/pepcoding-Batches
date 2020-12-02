class l001 {
    //Leetcode 75
    public void sortColors(int[] arr) {
             if (arr.length == 0)
        return;

    int p1 = -1, idx = 0, n = arr.length, p2 = n - 1;
    while (idx <= p2)
    {
        if (arr[idx] == 0)
            swap(arr,++p1, idx++);
        else if (arr[idx] == 2)
            swap(arr,idx, p2--);
        else idx++;
    } 
    }
    
    public void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        
    }

    public void reverse(int[] arr,int i,int j){
        while(i<j){
            swap(arr,i++,j--);
        }
    }

    public void rotate(int[] arr, int k) {
        if(arr.length == 0) return;
        
        k = (k % n + n) % n;

        int n = arr.length;
        reverse(arr,0 , n - 1);
        reverse(arr,0, k - 1);
        reverse(arr, k , n - 1);
    }

    public int maSumInRotation(int[] arr){

        int sum = 0,rotatedSum = 0, n = arr.length;
        
        for(int ele : arr) sum += ele;
        for(int i = 0;i<arr.length;i++)  rotatedSum += arr[i] * i;

        int maxSum = rotatedSum;
        // for(int i = 0;i<arr.length - 1;i++)
        //     maxSum = Math.max(maxSum, rotatedSum =  rotatedSum - sum + arr[i] * n);

        for(int i = 0;i<arr.length - 1;i++){
            rotatedSum  = rotatedSum - sum + arr[i] * n;
            maxSum = Math.max(maxSum, rotatedSum - sum + arr[i] * n);
        }

        return maxSum;
    }
}