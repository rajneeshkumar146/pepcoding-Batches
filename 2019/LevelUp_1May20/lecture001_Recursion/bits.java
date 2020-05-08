public class bits{
    public static void main(String[] args){

    }

    public static int countAllSetBits(int num){
        int count=0;
        for(int i=0;i<32;i++){
            int mask = (1<<i);
            if((num&mask)!=0) count++;
        }
        return count;
    }

    public static void countAllSetBits_02(int num){
        int count=0;
        while(num!=0){
            if((num & 1)!=0) count++;
            num >>>= 1;
        }
    }

    
    public static void countAllSetBits_03(int num){
        int count=0;
        while(num!=0){
            count++;
            num &=(num-1);
        }
    }

    public static int UniqueNumberInArray(int[] arr){
        int res=0;
        for(int ele: arr) res^=ele;
        return res;

    }
    
    //Leetcode : 231============================================
    public static bool isPowerOfTwo(int n) {
        return n>0 && (n & (n - 1))==0;
    }
}