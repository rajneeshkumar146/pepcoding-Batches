#include <iostream>
#include <string>

using namespace std;

int main(int args, char **argv)
{
    solve();
    return 0;
}

 void experimentOne() {
        string str = "";
        for (int i = 0; i < 100; i++) {
            str += to_string(i);
        }

        cout<<str<<endl;
    }


