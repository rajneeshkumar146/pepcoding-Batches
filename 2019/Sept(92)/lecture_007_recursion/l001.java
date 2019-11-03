public class l001 {
    public static void main(String[] args) {
        // DecreasingOder(10);
        // System.out.println(factorial(5));
        // int ans=factorial(5);
        // System.out.println(ans);

        // System.out.println(power_idiot(2, 10));
        // System.out.println(euler(5,0));
        String[] words={" ","abc","def","ghi","jkl","mno","pqrs","tuv","wx","yz","*","#"};
        System.out.println(nokiaKeyPad(words,"456",""));
    }

    public static void DecreasingOder(int n) {
        if (n == 0) {
            System.out.println("BASE-> " + n);
            return;
        }

        System.out.println("HI-> " + n);
        DecreasingOder(n - 1);
        System.out.println("BYE-> " + n);
    }

    public static int factorial(int n) {
        if (n == 1) return 1;

        int smallAns = factorial(n - 1);
        return smallAns * n;

    }

    public static int power_idiot(int a, int b) {
        if (b == 1) return a;

        int smallAns = power_idiot(a, b - 1);
        return smallAns * a;
    }


    public static int power_Better(int a, int b) {
        if (b == 1) return a;

        int smallAns = power_Better(a, b/2);
        smallAns*=smallAns;

        return (b%2!=0?smallAns*a:smallAns);
    }


    public static int fibo(int n) {
        if (n <= 1) return n;
        return fibo(n-1) + fibo(n-2);
    }

    public static int euler(int n,int level){
      if(n<=1){
          System.out.println("Base: "+ n +"@" + level);
          return n;
      }
      
      System.out.println("Pre: "+ n + "@" +level);

      int a=euler(n-1,level+1);
      System.out.println("In: "+ n + "@" +level);
      int b=euler(n-2,level+1);
      
      System.out.println("Post: "+ n+ "@" +level);

      return a+b+3;
    }

    public static int euler2(int n,int level){
        if(n<=2){
            System.out.println("Base: "+ n*level);
            return n;
        } 
        System.out.println("Pre: "+ n*level);
        int a=euler2(n-1,level+1);
        
        System.out.println("In1: "+ n*level);
        int b=euler2(n-2,level+1);
        
        System.out.println("In2: "+ n*level);
        int c=euler2(n-3,level-1);

        System.out.println("Post: "+ n*level);
        return a+b+c+3;
      }

     
      public static int nokiaKeyPad(String[] words,String num,String ans){
            if(num.length()==0){
             System.out.print(ans + " ");
             return 1; 
            }
    
        char ch=num.charAt(0);
        String newNum=num.substring(1);
        String word=words[ch-'0'];
        int count=0;

         for(int i=0;i<word.length();i++){
          count+=nokiaKeyPad(words,newNum,ans + word.charAt(i));
        }
         return count;
              }









}