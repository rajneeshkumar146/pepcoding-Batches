#include <iostream>
#include <queue>
#include <vector>
#include <string>
using namespace std;

class node
{
public:
    int freq = 0;
    string data = "";
    node *left = NULL;
    node *right = NULL;
    node(int freq, string data, node *left, node *right)
    {
        this->data = data;
        this->freq = freq;
        this->left = left;
        this->right = right;
    }
};

class MyCompare
{
public:
    bool operator()(node *a, node *b)
    {
        return (*a).freq > (*b).freq;
    }
};

void tree(priority_queue<node *, vector<node *>, MyCompare> &pq)
{
    while (pq.size() != 1)
    {
        node *n1 = pq.top();
        cout << n1->freq << " ";
        pq.pop();
        node *n2 = pq.top();
        cout << n2->freq << " ";
        pq.pop();
        int f1 = n1->freq;
        int f2 = n2->freq;
        int tot_ = f1 + f2;
        string final = n1->data + n2->data;
        node *f = new node(tot_, final, n1, n2);
        pq.push(f);
        cout << endl;
    }
}
void initialise(string s)
{
    priority_queue<node *, vector<node *>, MyCompare> pq;
    vector<int> freq(26, 0);
    for (int i = 0; i < s.size(); i++)
    {
        int idx = s[i] - 'a';
        freq[idx]++;
    }

    for (int j = 0; j < freq.size(); j++)
    {
        if (freq[j] > 0)
        {
            // cout<<"freq"<<j<<" ";
            // cout<<freq[j]<<" ";
            string d = "";
            d += char(j + 'a');
            //cout<<char(j+'a')<<" ";
            // cout<<d<<" ";
            node *g = new node(freq[j], d, NULL, NULL);
            pq.push(g);
        }
    }
    tree(pq);
}

int main()
{
}