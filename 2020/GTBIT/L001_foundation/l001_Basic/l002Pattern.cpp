#include<iostream>
using namespace std;

void printSquare(int row){
    int nst = row; // no of star.
    for(int r = 1;r <= row; r++){
        for(int cst = 1;cst <= nst;cst++){
            cout<<"*";
        }

        cout<<endl;
    }
}

void printTriangle(int row){
    int nst = 1; // no of star.
    for(int r = 1;r <= row; r++){
        for(int cst = 1;cst <= nst;cst++){
            cout<<"*";
        }

        nst++;
        cout<<endl;
    }
}

void printMirrorTriangle(int row){
    int nst = 1; // no of star.
    int nsp = row - 1;
    for(int r = 1;r <= row; r++){
        
        for(int csp = 1;csp <= nsp;csp++){
            cout<<" ";
        }

        for(int cst = 1;cst <= nst;cst++){
            cout<<"*";
        }

        nst++;
        nsp--;
        cout<<endl;
    }
}

void printDiamond(int row){
    int nst = 1; // no of star.
    int nsp = row / 2;
    for(int r = 1;r <= row; r++){
        for(int csp = 1;csp <= nsp;csp++){
            cout<<" ";
        }

        for(int cst = 1;cst <= nst;cst++){
            cout<<"*";
        }

        if(r <= row / 2){
            nsp--;
            nst+=2;
        }else{
            nsp++;
            nst-=2;
        }

        cout<<endl;
    }
}


int main(){
    // printSquare(10);
    // printTriangle(7);
    // printMirrorTriangle(7);
    printDiamond(7);
    return 0;
}