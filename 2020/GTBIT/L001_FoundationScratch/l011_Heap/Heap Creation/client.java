public class client{

    public static void solve1(int[] arr){
        heap pq = new heap();
        
        for(int ele : arr) pq.add(ele);

        while(pq.size() != 0){
            System.out.print(pq.remove() + " ");
        }
    }

    public static void solve2(int[] arr){
        heap pq = new heap(arr,false);

        while(pq.size() != 0){
            System.out.print(pq.remove() + " ");
        }
    }

    public static void main(String[] args){
        int[] arr = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13 };
        solve2(arr);
    }

}