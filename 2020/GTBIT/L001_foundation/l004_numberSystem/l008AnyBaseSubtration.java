public class l008AnyBaseSubtraction{
    public static int anyBaseSubtraction(int n,int m,int b){
        int res = 0, pow = 1, borrow = 0;
        while(n != 0 || m !=0){
            int r1 = n % 10;
            int r2 = m % 10;

            n /= 10;
            m /= 10;

            int subtract = r2 - r1 + borrow;
            if(subtract < 0){
                subtract += b;
                borrow = -1;
            }else borrow = 0;

            res += subtract * pow;
            pow *= 10;
        }

        return res;
    }


    public static void main(String[] args){
        int b = scn.nextInt();
        int n = scn.nextInt();
        int m = scn.nextInt();

        System.out.println(anyBaseSubtraction(n,m,b));
    }
}