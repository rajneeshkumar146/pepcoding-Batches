#include <iostream>
#include <vector>

using namespace std;

vector<vector<int>> vis;

// vector<vector<int>> dir{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
// vector<string> dirS{"D", "L", "U", "R"};

vector<vector<int>> dir{{1, 0}, {0, -1}, {-1, 0}, {0, 1}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
vector<string> dirS{"D", "L", "U", "R", "S", "N", "E", "W"};

int floodfill(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    vis[sr][sc] = 1;
    for (int d = 0; d < dir.size(); d++)
    {
        int r = sr + dir[d][0];
        int c = sc + dir[d][1];

        if (r >= 0 && c >= 0 && r < vis.size() && c < vis[0].size() && vis[r][c] == 0)
            count += floodfill(r, c, er, ec, ans + dirS[d]);
    }

    vis[sr][sc] = 0;
    return count;
}

int floodfill_jump(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    vis[sr][sc] = 1;
    for (int jump = 1; jump <= max(er, ec); jump++)
    {
        for (int d = 0; d < dir.size(); d++)
        {
            int r = sr + jump * dir[d][0];
            int c = sc + jump * dir[d][1];

            if (r >= 0 && c >= 0 && r < vis.size() && c < vis[0].size() && vis[r][c] == 0)
                count += floodfill_jump(r, c, er, ec, ans + dirS[d] + to_string(jump) + " ");
        }
    }

    vis[sr][sc] = 0;
    return count;
}

class pathPair
{
public:
    string path;
    int len;

    pathPair(string path, int len)
    {
        this->path = path;
        this->len = len;
    }
};

pathPair floodfillShortestPath_jump(int sr, int sc, int er, int ec)
{
    if (sr == er && sc == ec)
    {
        pathPair p("", 0);
        return p;
    }                                       

    int count = 0;
    vis[sr][sc] = 1;
    pathPair ans("", 1e8);
    for (int jump = 1; jump <= max(er, ec); jump++)
    {
        for (int d = 0; d < dir.size(); d++)
        {
            int r = sr + jump * dir[d][0];
            int c = sc + jump * dir[d][1];

            if (r >= 0 && c >= 0 && r < vis.size() && c < vis[0].size() && vis[r][c] == 0)
            {
                pathPair recAns = floodfillShortestPath_jump(r, c, er, ec);
                if (recAns.len + 1 < ans.len)
                {
                    ans.len = recAns.len + 1;
                    ans.path = dirS[d] + to_string(jump) + " " + recAns.path;
                }
            }
        }
    }

    vis[sr][sc] = 0;
    return ans;
}

void pathSet()
{
    int n = 3;
    int m = 3;
    vis.resize(n, vector<int>(m, 0));
    // cout << floodfill(0, 0, 2, 2, "") << endl;
    // cout << floodfill_jump(0, 0, 2, 2, "") << endl;
    pathPair ans = floodfillShortestPath_jump(0, 0, 2, 2);
    cout << ans.path << " -> " << ans.len << endl;
}

//Sudoku.=======================================================================================
vector<vector<int>> board;

void print2D(){
    for(vector<int> & a: board){
        for(int ele: a){
            cout<<ele<<" ";
        }
        cout<<endl;
    }
}

bool isSafeToPlaceNumber(int r,int c,int num){
    //Row
    for(int i=0;i<9;i++){
        if(board[i][c]==num) return false;
    }

    //Col
    for(int i=0;i<9;i++){
        if(board[r][i]==num) return false;
    }

    //Mat
    r = (r/3)*3;
    c = (c/3)*3;
    for(int i=0;i<3;i++){
        for(int j=0;j<3;j++){
            if(board[r + i][c + j] == num) return false;
        }
    }


    return true;
}

int sudokuSolver(int vidx,vector<int>& loc){
    if(vidx == loc.size()){
        print2D();
        return 1;   
    }

    int idx = loc[vidx];
    int r = idx / 9;
    int c = idx % 9;
    
    int count=0;
    for(int num = 1; num<=9;num++){
        if(isSafeToPlaceNumber(r,c,num)){
            board[r][c] = num;
            count+=sudokuSolver(vidx+1,loc);
            board[r][c] = 0;
        }
    }

    return false;
}

void sudoku(){
    vector<int> loc;
    for(int i=0;i<9;i++){
        for(int j=0;j<9;j++){
            if(board[i][j] == 0){
                loc.push_back(i*9+j);
            }
        }
    }
    
    sudokuSolver(0,loc);
}

void solve()
{
    pathSet();
}

int main()
{
    solve();
}