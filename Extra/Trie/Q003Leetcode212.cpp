#include <iostream>
#include <vector>

using namespace std;

class Node
{
public:
    bool WordEnd = 0;
    string word = "";
    vector<Node *> childs;

    Node()
    {
        this->childs.assign(26, nullptr);
        this->WordEnd = false;
        this->word = "";
    }
};

Node *root = nullptr;

void insert(string &word)
{
    Node *curr = root;
    int n = word.length();

    for (int i = 0; i < n; i++)
    {
        int idx = word[i] - 'a';
        if (curr->childs[idx] == nullptr)
            curr->childs[idx] = new Node();
        curr = curr->childs[idx];
    }
    curr->WordEnd = true;
    curr->word = word;
}

int dirA[4][2] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
vector<string> ans;
int n, m;

void dfs(int r, int c, Node *node, vector<vector<char>> &board)
{
    if (node->WordEnd)
    {
        node->WordEnd = false;
        ans.push_back(node->word);
    }

    char ch = board[r][c];
    board[r][c] = '$';
    for (int d = 0; d < 4; d++)
    {
        int x = r + dirA[d][0];
        int y = c + dirA[d][1];
        if (x >= 0 && y >= 0 && x < n && y < m && board[x][y] != '$'
         && node->childs[board[x][y] - 'a'] != nullptr)
            dfs(x, y, node->childs[board[x][y] - 'a'], board);
    }
    board[r][c] = ch;
}

vector<string> findWords(vector<vector<char>> &board, vector<string> &words)
{
    if (!board.size() || !board[0].size() || !words.size())
        return {};

    Node *root = new Node();
    for (string &word : words)
    {
        insert(word);
    }

    n = board.size();
    m = board[0].size();
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (root->childs[board[i][j] - 'a'] = nullptr)
                dfs(i, j, root->childs[board[i][j] - 'a'], board);
        }
    }

    return ans;
}
