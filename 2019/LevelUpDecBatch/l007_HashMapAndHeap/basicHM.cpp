#include <iostream>
#include <queue>
#include <vector>
#include <unordered_map>

using namespace std;

void test1()
{
    unordered_map<string, int> map;
    map["USA"] = 1000;
    map["IND"] = 10000;
    map["NEP"] = 90;
    map["usa"] = 9000;

    // map["Usa"] = 9000;
    cout << map["USA"] << endl;
    if (map.find("usa") != map.end())
        cout << map["usa"] << endl;

    for (pair<string, int> p : map)
        cout << p.first << " -> " << p.second << endl;
}

// Frequency map of given string
void test2(string str)
{
    unordered_map<char, int> map;
    for (char ch : str)
    {
        map[ch]++;
    }
}

// Index of all character
void test3(string str)
{
    unordered_map<char, vector<int>> map;
    for (int i = 0; i < str.length(); i++)
    {
        char ch = str[i];
        map[ch].push_back(i);
    }

    for (auto &key : map)
    {
        char k = key.first;
        vector<int> val = key.second;
        cout << k << " -> ";
        for (int ele : val)
            cout << ele << ", ";
        cout << endl;
    }
}

int main()
{
    test3("abcaksjbabaabadabab");
    return 0;
}
