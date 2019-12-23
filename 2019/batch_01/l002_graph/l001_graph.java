import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
public class l001_graph{

    public static Scanner scn=new Scanner(System.in);
    public static ArrayList<Edge>[] graph=new ArrayList[7];
    //    static ArrayList<ArrayList<Edge>> graph=new ArrayList<>();


    public static class Edge{
        int v=0;
        int w=0;

        Edge(int v,int w){
            this.v=v;
            this.w=w;
        }

        Edge(){

        }
    }

    public static void caseI(){
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<Edge>();
        }

        addEdge(0,3,10);
        addEdge(0,1,10);
        addEdge(1,2,10);
        addEdge(2,3,40);
        addEdge(3,4,2);
        addEdge(4,5,2);
        addEdge(4,6,3);
        addEdge(5,6,8);
    }

    // public static void caseII(){
    //     for(int i=0;i<7;i++){
    //         graph.add(new ArrayList<Edge>());
    //     }
    // }

    public static void addEdge(int u,int v,int w){
        if(u<0 || v< 0 || u>=graph.length || v>=graph.length) return;

        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w));
    }

    public static void display(){
        for(int i=0;i<graph.length;i++){
            System.out.print(i + " =>" );
            for(int j=0;j<graph[i].size();j++){
                System.out.print("("+graph[i].get(j).v + ", " + graph[i].get(j).w + "), ");
            }   
            System.out.println();
        }
    }

    public static class BFSpair{
        int vtx=0;
        int wsf=0;
        String psf="";

       BFSpair(int vtx,int wsf,String psf){
           this.vtx=vtx;
           this.wsf=wsf;
           this.psf=psf;
       }

        BFSpair(){

        }

    }

    public static void BFS_ShortestPath(int src,int dest){
        LinkedList<BFSpair> que=new LinkedList<>();
        boolean[] vis=new boolean[graph.length];
        
        BFSpair root=new BFSpair(src,0,src+"");
        que.addLast(root);
        que.addLast(null);

        int cycleCounter=0;
        boolean isPath=false;
        int level=1;

        while(que.size()!=1){
            //remove 

            

            BFSpair rpair=que.removeFirst();

            //cycle
            if(vis[rpair.vtx]){
                System.out.println("Cycle Number: "+cycleCounter + ": "+rpair.psf );
                cycleCounter++;
            }

            //mark.
            vis[rpair.vtx]=true;

            //destination.
            if(rpair.vtx==dest && !isPath){
                System.out.println(rpair.psf + " @ " + rpair.wsf + " -> " + level);
                isPath=true;
            }

            //nbr.
            for(Edge e: graph[rpair.vtx]){
                if(!vis[e.v]){ //unmark nbr.
                    BFSpair pair=new BFSpair(e.v,rpair.wsf+e.w,rpair.psf+"->"+e.v);
                    que.addLast(pair);
                }
            }

            if(que.getFirst()==null){
                que.removeFirst();
                que.addLast(null);
                level++;
            }

            //


        }
    }





    public static void main(String[] args){
        caseI();
        // display();

        BFS_ShortestPath(0,6);

    }






}