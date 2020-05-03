#include <iostream>
#include <vector>
#include <unordered_map>
using namespace std;

void set1()
{
    unordered_map<string, int> map;
    //put
    map["India"] = 10000;
    map["USA"] = 1000;
    map["china"] = 10;
    map["PAK"] = -100000;
    map["UK"] = 900;

    //get
    cout << map["USA"] << endl;

    //find
    if (map.count("Usa")) // return 0 or 1
        cout << map["Usa"] << endl;

    //erase
    map.erase("USA");
    map.erase("Usa");   // it will work.
    
    //print
    for (pair<string, int> key : map)
    {
        cout << key.first << " " << key.second << endl;
    }
}

int main()
{
    set1();
    return 0;
}