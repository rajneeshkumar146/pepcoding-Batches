#include <iostream>
#include <vector>
#include <queue>

using namespace std;

vector<int> par;
int findPar(int u)
{
    return par[u] == u ? u : (par[u] = findPar(par[u]));
}

//684
vector<int> findRedundantConnection(vector<vector<int>> &edges)
{
    int n = edges.size() + 1;
    for (int i = 0; i < n; i++)
        par.push_back(i);

    for (vector<int> &e : edges)
    {
        int u = e[0], v = e[1];
        int p1 = findPar(u), p2 = findPar(v);
        if (p1 != p2)
        {
            par[p1] = p2;
        }
        else
            return e;
    }

    return {};
}

//1061

string smallestEquivalentString(string s1, string s2, string baseStr)
{
    for (int i = 0; i < 26; i++)
        par.push_back(i);
    for (int i = 0; i < s1.length(); i++)
    {
        char c1 = s1[i], c2 = s2[i];
        int p1 = findPar(c1 - 'a'), p2 = findPar(c2 - 'a');
        par[p1] = min(p1, p2);
        par[p2] = min(p1, p2);
    }

    string ans = "";
    for (char ch : baseStr)
    {
        int p = findPar(ch - 'a');
        ans += (char)(p + 'a');
    }

    return ans;
}
