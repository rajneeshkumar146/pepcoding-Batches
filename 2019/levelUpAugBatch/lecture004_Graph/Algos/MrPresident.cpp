#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;
long long n,m,k;
vector<vector<long long>> edges;

vector<int> par;
int findPar(int u)
{
    if (par[u] == u)
        return u;
    return par[u] = findPar(par[u]);
}

long long unionFind()
{
    for (int i = 0; i <= n; i++) par.push_back(i);
    
	long long cost = 0;
	vector<vector<long long>> arr;
    for (vector<long long> a : edges) // a = {u,v,w};
    {
        int gp1 = findPar(a[0]); // par of u
        int gp2 = findPar(a[1]); // par of v
         
        if (gp1 != gp2)
        {
            par[gp1] = gp2;
			cost += a[2];
			arr.push_back(a);
            n--;
		}
    }

	if(n > 1) return -1;

	long long superRoadCount = 0;
	for(int i = arr.size()-1;i>=0;i--){
        if(cost <= k) break;
		cost = (cost - arr[i][2] + 1);
	    superRoadCount++;
	}


	return cost <= k ? superRoadCount : -1;
	}



int main(){
    
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

	cin >>n>>m>>k;
	for(int i=0;i<m;i++){
		long long a,b,c; cin >>a>>b>>c;
		edges.push_back({a,b,c});
	}

	sort(edges.begin(),edges.end(),[](auto& a,auto& b){
		return a[2] < b[2];
	});

	cout<<unionFind()<<endl;
	return 0;
}
