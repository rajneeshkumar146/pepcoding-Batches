#include <iostream>
#include <vector>

using namespace std;

class Edge
{
public:
    int v = 0;
    int w = 0;

    Edge(int v, int w)
    {
        this->v = v;
        this->w = w;
    }
};

const int N = 7;
vector<vector<Edge>> graph(N);

void addEdge(int u, int v, int w)
{
    graph[u].push_back(Edge(v, w));
    graph[v].push_back(Edge(u, w));
}

void display(){

}

void removeEdge(int u,int v){

}

void removeVtx(int u){

}


void constructGraph(){
   
    // for(int i =0;i<e;i++){
    //     int u,v,w; cin >> u >>v>>w;
    //     addEdge(u,v,w);
    // }
   
    addEdge(0,1,10);
    addEdge(0,3,10);
    addEdge(1,2,10);
    addEdge(2,3,40);
    addEdge(3,4,2);
    addEdge(4,5,2);
    addEdge(4,6,8);
    addEdge(5,6,3);
}

int main()
{
    return 0;
}
