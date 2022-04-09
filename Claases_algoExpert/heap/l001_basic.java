import java.util.*;

public class l001_basic {

    public static List<Integer> mergeTwoSortedArrays(List<Integer> arr1, List<Integer> arr2) {
        int n = arr1.size(), m = arr2.size();
        if (n == 0 || m == 0)
            return n == 0 ? arr2 : arr1;

        List<Integer> ans = new ArrayList<>();
        int i = 0, j = 0;

        while (i < n && j < m) {
            if (arr1.get(i) < arr2.get(j))
                ans.add(arr1.get(i++));
            else
                ans.add(arr2.get(j++));
        }

        while (i < n)
            ans.add(arr1.get(i++));
        while (j < m)
            ans.add(arr2.get(j++));

        return ans;
    }

    public static List<Integer> mergeSortedArrays(List<List<Integer>> arr,int l,int r) {
        if(l == r) return arr.get(l);
        int mid = (l + r) / 2;
        return mergeTwoSortedArrays(mergeSortedArrays(arr, l, mid), mergeSortedArrays(arr, mid + 1, r));
    }


    // best time complexity -> O(N.logn), N : n.l, l is avg length of array
    public static List<Integer> mergeSortedArrays(List<List<Integer>> arrays) {
        int n = arrays.size();
        if(n == 0) return new ArrayList<>();

        return mergeSortedArrays(arrays, 0, n - 1);
    }

    // brute force : O(N.n), N : n.l
    public static List<Integer> mergeSortedArrays_(List<List<Integer>> arrays) {
        int n = arrays.size();
        if(n == 0) return new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < n;i++)
          ans = mergeTwoSortedArrays(ans, arrays.get(i));

        return ans;
    }


}
