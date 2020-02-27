#include <iostream>
#include <vector>
#include <algorithm>

#define pii pair<int, int>
#define piii pair<int, pii>

using namespace std;

void display(vector<vector<pii>> &gp)
{
    for (int i = 0; i < gp.size(); i++)
    {
        cout << i << " -> ";
        for (pii e : gp[i])
        {
            cout << "(" << e.first << ", " << e.second << ") ";
        }
        cout << endl;
    }
}

void merge(int p1, int p2, vector<int> &par, vector<int> &size)
{
    if (size[p1] < size[p2])
    {
        par[p1] = p2;
        size[p2] += size[p1];
    }
    else
    {
        par[p2] = p1;
        size[p1] += size[p2];
    }
}

int findPar(int vtx, vector<int> &par)
{
    if (par[vtx] == vtx)
        return vtx;

    par[vtx] = findPar(par[vtx], par);
    return par[vtx];

    // return ((par[vtx] != vtx) ? par[vtx] = findPar(par[vtx], par) : vtx);
}

void kruskal(vector<piii> &arr, int n, vector<int> &par, vector<int> &size)
{

    vector<vector<pii>> newGraph(n, vector<pii>());

    for (int i = 0; i < arr.size(); i++)
    {
        piii rp = arr[i];

        int p1 = findPar(rp.second.first, par);
        int p2 = findPar(rp.second.second, par);

        if (p1 != p2)
        {
            merge(p1, p2, par, size);

            newGraph[rp.second.first].push_back({rp.second.second, rp.first});
            newGraph[rp.second.second].push_back({rp.second.first, rp.first});
        }
    }

    display(newGraph);
}

void kruskal()
{
    int n = 7;
    vector<piii> arr;

    arr.push_back({10, {0, 1}});
    arr.push_back({10, {0, 3}});
    arr.push_back({10, {1, 2}});
    arr.push_back({40, {2, 3}});
    arr.push_back({2, {3, 4}});
    arr.push_back({2, {4, 5}});
    arr.push_back({3, {4, 6}});
    arr.push_back({8, {5, 6}});

    sort(arr.begin(), arr.end(), [](piii a, piii b) {
        return a.first < b.first;
    });

    vector<int> par(n, 0);
    vector<int> size(n, 1);
    for (int i = 0; i < n; i++)
    {
        par[i] = i;
    }

    kruskal(arr, n, par, size);
}

void solve()
{

     vector<pii> arr;
     for(int i=0;i<10;i++){
         arr.push_back({10-i,i});
     }

     sort(arr.begin(),arr.end(),[](pii a,pii b){
         return a.first < b.first;
     });

     for(pii ele: arr){
         cout<<ele.first <<" "<<ele.second<<endl;
     }
    // kruskal();
}

int main()
{
    solve();
    return 0;
}
