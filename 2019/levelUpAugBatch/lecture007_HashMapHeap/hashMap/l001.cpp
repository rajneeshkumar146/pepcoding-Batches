#include<iostream>
#include<vector>
#include<unordered_map>

using namespace std;

void set1(){
    unordered_map<string,int> map;
    map["IND"] = 10000;
    map["USA"] = 100;
    map["UK"] = 990;
    map["NEP"] = 1009;
    map["GER"] = 1899;
    map["UK"] = 10078;

    for(pair<string,int> key : map){
        cout << key.first << " : "<<key.second<<endl;
    }
    cout<<endl;

    if(map.find("INd") != map.end())
        cout << map["INd"] << endl;
    else
       cout << "Not Present" << endl;

    map.erase("GER");
    for(pair<string,int> key : map){
        cout << key.first << " : "<<key.second<<endl;
    }
}

void freqMap(string str){
    unordered_map<char,int> map;
    for(char ch : str) map[ch]++;
}

void freqMap2(string str){
    unordered_map<char,vector<int>> map;
    for(int i=0;i<str.length();i++) map[str[i]].push_back(i);
}

int main(){
    set1();
    return 0;
}