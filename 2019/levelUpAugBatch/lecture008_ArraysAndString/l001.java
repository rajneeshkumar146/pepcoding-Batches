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

    
public int lengthOfLongestSubstring(string s)
{
    if (s.length() <= 1)
        return s.length();

    int n = s.length(), si = 0, ei = 0, count = 0,len = 0;
    int Maxsi = 0, Maxei = 0;
    int[] map = new int[128];

    while(ei < n){
        if(map[s.charAt(ei++)]++ > 0) count++;

        while(count > 0){
            if(map[s.charAt(si++)]-- > 1) count--;
        }

        // len = Math.max(len,ei - si);
        if(ei - si > len){
            len  = ei - si;
            Maxei = ei;
            Maxsi = si;
        }
    }

    return len;
}

public String minWindow(String s, String t) {
    int ns = s.length();
    int nt = t.length();
    
    int[] freq = new int[128];
    for(int i=0;i<nt;i++) freq[t.charAt(i)]++;
    
    int requirement = nt, si = 0,ei = 0, len = (int)1e8;
    int head = 0;
    
    while(ei < ns){
        if(freq[s.charAt(ei++)]-- > 0) requirement--;
        
        while(requirement == 0){
            if(ei - si < len) len = ei - (head = si);
            
            if(freq[s.charAt(si++)]++ == 0) requirement++;
        }
    }
    
    return len == (int)1e8 ? "" : s.substring(head, head + len); 
}

}