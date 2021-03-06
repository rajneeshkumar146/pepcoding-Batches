#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

vector<int> par;
int findPar(int u)
{
    return par[u] == u ? u : par[u] = findPar(par[u]);
}

int mrPresident(int n,vector<vector<int>> &edges,long k){
	for(int i=0;i<=n;i++) par.push_back(i);

    vector<int> mstEdgeWeight;
	long overallWeightSum = 0;
	for(vector<int>& e : edges){
		int p1 = findPar(e[0]);
		int p2 = findPar(e[1]);

		if(p1 != p2){
			par[p1] = p2;
			mstEdgeWeight.push_back(e[2]);
		    overallWeightSum += e[2];
			n--;
		}
	}

	if(n > 1) return -1;
	if(overallWeightSum <= k) return 0;

    int transform = 0;
	for(int i = mstEdgeWeight.size() - 1; i >= 0;i--){
		overallWeightSum = overallWeightSum - mstEdgeWeight[i] + 1;
		transform++;

		if(overallWeightSum <= k) break;
	}

	return overallWeightSum <= k ? transform : -1;
}


void mrPresident(){
    ios_base::sync_with_stdio(false);
	long n,m,k; cin >> n >> m >> k;

	vector<vector<int>> edges;
	for(int i = 0;i < m;i++){
		int u,v,w; cin >> u >> v >> w;
		edges.push_back({u,v,w});
	}

	sort(edges.begin(),edges.end(),[](vector<int>& a,vector<int>& b){
		return a[2] < b[2];
	});


	cout << mrPresident(n,edges,k)<<endl;

}

int main(){
	mrPresident();
	return 0;
}
