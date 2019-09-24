import java.util.ArrayList;
import java.util.Collections;
public class topologicalSort{

    public static void main(String[] args){
         
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            graph.add(new ArrayList<>());
        }

        addEdge(graph, 5, 2);
        addEdge(graph, 5, 0);
        addEdge(graph, 2, 3);
        addEdge(graph, 3, 1);
        addEdge(graph, 4, 1);
        addEdge(graph, 4, 0);

        topoSort(graph);

    }

    private static class Edge {
        int v1 = 0;
        int v2 = 0;

        private Edge(int v1, int v2) {
            this.v1 = v1;
            this.v2 = v2;
        }
    }

    public static void addEdge(ArrayList<ArrayList<Edge>> g, int v1, int v2) {
        g.get(v1).add(new Edge(v1, v2));
    }

    public static void display(ArrayList<ArrayList<Edge>> graph) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < graph.size(); i++) {
            for (Edge e : graph.get(i)) {
                sb.append("(" + e.v1 + "," + e.v2 + ")" + " ");
            }

            sb.append("\n");
        }

        System.out.print(sb.toString() + "\n");
    }

    public static void topoSort(ArrayList<ArrayList<Edge>> graph){
        boolean[] isVisited =new boolean[graph.size()];
        ArrayList<Integer> topoOder=new ArrayList<>();

        for(int i=0;i<isVisited.length;i++){
            if(!isVisited[i]){
                topoSort(graph,i,isVisited,topoOder);
            }

        } 

        Collections.reverse(topoOder);
        System.out.println(topoOder);
    }

    public static void topoSort(ArrayList<ArrayList<Edge>> graph,int vtx,boolean[] isVisited,ArrayList<Integer> topoOder){
      isVisited[vtx]=true;
       for(Edge e:graph.get(vtx)){
           if(!isVisited[e.v2]){
               topoSort(graph,e.v2,isVisited,topoOder);
           }
       }
       topoOder.add(vtx);


    }






}