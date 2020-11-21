import java.util.ArrayList;
public class L001{

    public static void printIncr(int a,int b){
        if(a == b + 1) return;

        System.out.print(a+ " ");
        printIncr(a+1,b);
    }

    public static void printDecr(int a,int b){
        if(a == b + 1) return;

        printDecr(a+1,b);
        System.out.print(a+ " ");
    }

    public static void printDecr(int a,int b){
        if(a == b + 1) return;
        
        if(a % 2 == 0) System.out.print(a+ " "); 
        printDecr(a+1,b);
        if(a % 2 != 0) System.out.print(a+ " ");
    }

    public static int fact(int n){
        return n == 0 ? 1 : n * fact(n-1);
    }

    public static int power(int a,int b){
        return b == 0 ? 1 : power(a, b - 1) * a;
    }

    public static int powerBtr(int a,int b){
        if(b == 0) return 1;
        int val = power(a,b/2);
        val *= val;
        return b % 2 != 0 ? val * a : val;
    }

    public static ArrayList<String> subseq(String str,int idx){
        if(str.length() == 0){
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        char ch = str.charAt(idx);
        ArrayList<String> smallAns = subseq(str,idx+1);
        ArrayList<String> myAns = new ArrayList<>();

        for(String s : smallAns){
            myAns.add(s);
            myAns.add(ch + s);
        }

        return myAns;
    }

    public static ArrayList<String> subseq2(String str,int idx){
        if(idx == str.length()){
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        char ch = str.charAt(idx);
        ArrayList<String> smallAns = subseq2(str,idx+1);
        int size = smallAns.size();

        for(int i=0;i<size;i++){
            smallAns.add(ch + smallAns.get(i));
        }

        return smallAns;
    }

    public static int subseq3(String str,int idx,String ans){
        if(idx == str.length()){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        count += subseq3(str,idx+1,ans);
        count += subseq3(str,idx+1,ans + str.charAt(idx));

        return count;
    }

    public void permute(int[] nums,List<List<Integer>> ans,List<Integer> res,boolean[] vis) {
        if(res.size() == nums.length){
            ArrayList<Integer> base = new ArrayList<>(res);
            ans.add(base);
            return;
        }

        for(int i=0;i<nums.length;i++){
            if(!vis[i]){
                vis[i] = true;
                res.add(nums[i]);
                permute(nums,ans,res,vis);
                res.remove(res.size() - 1);    
                vis[i] = false;
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> res = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] vis = new boolean[nums.length];
        
        permute(nums,ans,res,vis);
        return ans;
    }




}