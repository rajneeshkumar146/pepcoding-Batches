#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

void basics_01()
{
    unordered_map<string, int> map;
    map["IND"] = 10000;
    map["USA"] = 10000;
    map["CHINA"] = -10000;
    map["NEPAL"] = 10000;
    map["GERMANY"] = 10000;
    map["IND"] = 10000;
    map["IND"] = 10000;

    cout << map.count("IND") << endl;

    for (pair<string, int> key : map)
    {
        cout << map[key.first] << " ";
    }
}

void freqMap(string str)
{
    unordered_map<char, int> map;
    for (int i = 0; i < str.length(); i++)
    {
        char ch = str[i];
        map[ch]++;
    }
}

void freqMapOfIndx(string str)
{
    unordered_map<char, vector<int>> map;
    for (int i = 0; i < str.length(); i++)
    {
        char ch = str[i];
        map[ch].push_back(i);
    }

    for (pair<char, vector<int>> key : map)
    {
        vector<int> ar = key.second;
        cout << key.first << " -> ";
        for (int ele : ar)
            cout << ele << " ";
        cout << endl;
    }
}

int main()
{
    // basics_01();
    freqMapOfIndx("ababnababababababaabab");
    return 0;
}