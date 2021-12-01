public class functionsTypes {
    //Sum Function

    //1. RT - none , P - none
    public static void sum(){
        int a = 5;
        int b = 10;
        System.out.println(a+b);
    }
    //2. RT - !none , P - none
    public static int sum(){
        int a = 50;
        int b = 10;
        return a + b;
    }
    //3. RT - !none , P - !none
    public static int sum(int a, int b){
            return a + b;
    }
    //4. RT - none , P - !none
    public static void sum(int a, int b){
        System.out.println(a+b);
    }

    public static void main(String [] args){
        // int ans = sum();

        int ans = sum(10,60);

        System.out.println(ans);
    }
}
