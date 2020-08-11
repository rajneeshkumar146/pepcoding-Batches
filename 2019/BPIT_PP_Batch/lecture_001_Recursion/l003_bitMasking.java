public class l003_bitmasking{

    public static int offOn(int n,int k){
        int mask = (1 << k);
        n = ( n | mask);
        return n;
    }

    
    public static int onOff(int n,int k){
        int mask = (~(1 << k));
        n = ( n & mask);
        return n;
    }


}