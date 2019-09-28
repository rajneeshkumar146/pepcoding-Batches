#include <iostream>
#include <string>
#include <vector>

using namespace std;

//sudoku.============================================================
void display(vector<vector<int>> &boxe)
{
    for (vector<int> ar : boxe)
    {
        for (int ele : ar)
        {
            cout << ele << " ";
        }
        cout << endl;
    }
    cout << endl;
}

bool isValidToPlaceNumber(vector<vector<int>> &boxe, int num, int r, int c)
{
    //col
    for (int i = 0; i < boxe[0].size(); i++)
    {
        if (boxe[r][i] == num)
            return false;
    }

    //row
    for (int i = 0; i < boxe.size(); i++)
    {
        if (boxe[i][c] == num)
            return false;
    }

    int sr = (r / 3) * 3;
    int sc = (c / 3) * 3;
    // cout<<sr<<" "<<sc<<endl;
    for (int i = sr; i < sr + 3; i++)
    {
        for (int j = sc; j < sc + 3; j++)
        {
            if (boxe[i][j] == num)
                return false;
        }
    }

    return true; //we can place number.
}

int sudoku_01(vector<vector<int>> &boxe, int bno, int countOfUnfiledLocation)
{
    if (countOfUnfiledLocation == 0)
    // if (bno == 81)
    {
        display(boxe);
        return 1;
    }

    int count = 0;
    int r = bno / boxe[0].size();
    int c = bno % boxe[0].size();

    if (boxe[r][c] != 0)
        count += sudoku_01(boxe, bno + 1, countOfUnfiledLocation);
    else
    {
        for (int num = 1; num <= 9; num++)
        {
            if (isValidToPlaceNumber(boxe, num, r, c))
            {
                boxe[r][c] = num;
                count += sudoku_01(boxe, bno + 1, countOfUnfiledLocation - 1);
                if (count == 1)
                    return count;
                boxe[r][c] = 0;
            }
        }
    }

    return count;
}

int sudoku_02(vector<vector<int>> &boxe, vector<pair<int, int>> &zerosIdx, int idxOfZeros)
{
    if (idxOfZeros == zerosIdx.size())
    {
        display(boxe);
        return 1;
    }

    int r = zerosIdx[idxOfZeros].first;
    int c = zerosIdx[idxOfZeros].second;

    // int r = zerosIdx.get(idxOfZeros)[0];  //for java
    // int c = zerosIdx.get(idxOfZeros)[1];
    int count = 0;
    for (int num = 1; num <= 9; num++)
    {
        if (isValidToPlaceNumber(boxe, num, r, c))
        {
            boxe[r][c] = num;
            count += sudoku_02(boxe, zerosIdx, idxOfZeros + 1);
            if (count == 1)
                return count;
            boxe[r][c] = 0;
        }
    }
}

void sudoku()
{
    // vector<vector<int>> boxe = {{3, 0, 6, 5, 0, 8, 4, 0, 0},
    //                             {5, 2, 0, 0, 0, 0, 0, 0, 0},
    //                             {0, 8, 7, 0, 0, 0, 0, 3, 1},
    //                             {0, 0, 3, 0, 1, 0, 0, 8, 0},
    //                             {9, 0, 0, 8, 6, 3, 0, 0, 5},
    //                             {0, 5, 0, 0, 9, 0, 6, 0, 0},
    //                             {1, 3, 0, 0, 0, 0, 2, 5, 0},
    //                             {0, 0, 0, 0, 0, 0, 0, 7, 4},
    //                             {0, 0, 5, 2, 0, 6, 3, 0, 0}};

    vector<vector<int>> boxe = {{3, 0, 6, 5, 0, 0, 4, 0, 0},
                                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                                {0, 5, 0, 0, 0, 0, 6, 0, 0},
                                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                                {0, 0, 5, 0, 0, 6, 3, 0, 0}};

    vector<pair<int, int>> zerosIdx;
    // ArrayList<int[]> zerosIdx; //java.
    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
        {
            if (boxe[i][j] == 0)
            {
                zerosIdx.push_back({i, j}); //C++
                // int[] ar={i,j};   //java
                // zerosIdx.add(ar); //java.
            }
        }
    }
    // cout << sudoku_01(boxe, 0, countofZeros) << endl;
    cout << sudoku_02(boxe, zerosIdx, 0) << endl;
    // cout << sudoku_03(boxe, zeros, 1, 0) << endl;
}

//crypto.============================================

int getNumberFromString(string &str, vector<int> &freqMap)
{
    int num = 0;
    int pow = 1;
    for (int i = str.length() - 1; i >= 0; i--)
    {
        num += freqMap[str[i] - 'a'] * pow;
        pow *= 10;
    }
    return num;
}

bool checkCryptoIsValid(string &str1, string &str2, string &str3, vector<int> &freqMap)
{
    int num1 = getNumberFromString(str1, freqMap);
    int num2 = getNumberFromString(str2, freqMap);
    int num3 = getNumberFromString(str3, freqMap);

    int start1 = freqMap[str1[0] - 'a'];
    int start2 = freqMap[str2[0] - 'a'];
    int start3 = freqMap[str3[0] - 'a'];

    if (num1 + num2 != num3 || start1 == 0 || start2 == 0 || start3 == 0)
        return false;

    cout << " " << num1 << "\n"
         << "+" << num2 << endl;
    for (int i = 0; i < str3.length(); i++)
    {
        cout << "-";
    }
    cout << "\n"
         << num3 << endl;
    cout << endl;

    return true;
}

int cryptoArithmatic(string str, string &str1, string &str2, string &str3, vector<bool> &numMap, vector<int> &freqMap)
{
    if (str.length() == 0)
    {
        if (checkCryptoIsValid(str1, str2, str3, freqMap))
        {
            return 1;
        }
        return 0;
    }

    int count = 0;
    char ch = str[0];
    for (int num = 9; num >= 0; num--)
    {
        if (!numMap[num])
        {
            numMap[num] = true;
            freqMap[ch - 'a'] = num;

            count += cryptoArithmatic(str.substr(1), str1, str2, str3, numMap, freqMap);

            freqMap[ch - 'a'] = 0;
            numMap[num] = false;
        }
    }
    return count;
}

void crypto()
{
    string str1 = "send";
    string str2 = "more";
    string str3 = "money";

    vector<int> freqMap(26, 0);

    for (int i = 0; i < str3.length(); i++)
    {
        if (i < str1.length())
        {
            freqMap[str1[i] - 'a']++;
        }

        if (i < str2.length())
        {
            freqMap[str2[i] - 'a']++;
            // frewMap[str2.charAt(i)-'a']++;
        }
        freqMap[str3[i] - 'a']++;
    }

    //UniqueString.
    string str = "";
    for (int i = 0; i < 26; i++)
    {
        if (freqMap[i] > 0)
        {
            str += (char)(i + 'a');
        }
    }

    // cout << str << endl;

    vector<bool> numMap(10, false);
    vector<int> freqmap(26, 0);
    cout << cryptoArithmatic(str, str1, str2, str3, numMap, freqmap);
}

//crossWord.===================================================

bool canPlaceHorizontal(vector<vector<char>> &box, string &word, int r, int c)
{
    if (c + word.length() > box[0].size())
    {
        return false;
    }
    if (c - 1 >= 0 && box[r][c - 1] != '+')
        return false;

    if (c + word.length() < box[0].size() && box[r][c + word.length()] != '+')
    {
        return false;
    }

    for (int i = 0; i < word.length(); i++)
    {
        char ch = box[r][c + i];
        if (ch != '-' && ch != word[i])
        {
            return false;
        }
    }
    return true;
}

vector<bool> placeHorizontal(vector<vector<char>> &box, string &word, int r, int c)
{
    vector<bool> placedLocation(word.length(), false);
    for (int i = 0; i < word.length(); i++)
    {
        char ch = box[r][c + i];
        if (ch == '-')
        {
            box[r][c + i] = word[i];
            placedLocation[i] = true;
        }
    }

    return placedLocation;
}

void unPlaceHorizontal(vector<vector<char>> &box, vector<bool> &placedLocation, int r, int c)
{
    for (int i = 0; i < placedLocation.size(); i++)
    {
        if (placedLocation[i])
        {
            box[r][c + i] = '-';
            placedLocation[i] = false;
        }
    }
}

bool canPlaceVertical(vector<vector<char>> &box, string &word, int r, int c)
{
    if (r + word.length() > box.size())
        return false;
    if (r - 1 >= 0 && box[r - 1][c] != '+')
        return false;

    if (r + word.length() < box.size() && box[r + word.length()][c] != '+')
        return false;

    for (int i = 0; i < word.length(); i++)
    {
        char ch = box[r + i][c];
        if (ch != '-' && ch != word[i])
        {
            return false;
        }
    }
    return true;
}

vector<bool> placeVertical(vector<vector<char>> &box, string &word, int r, int c)
{
    vector<bool> placedLocation(word.length(), false);
    for (int i = 0; i < word.length(); i++)
    {
        char ch = box[r + i][c];
        if (ch == '-')
        {
            box[r + i][c] = word[i];
            placedLocation[i] = true;
        }
    }

    return placedLocation;
}

void unPlaceVertical(vector<vector<char>> &box, vector<bool> &placedLocation, int r, int c)
{
    for (int i = 0; i < placedLocation.size(); i++)
    {
        if (placedLocation[i])
        {
            box[r + i][c] = '-';
            placedLocation[i] = false;
        }
    }
}

int crossWordUtil(vector<vector<char>> &box, vector<string> &words, int idx)
{
    if (idx == words.size())
    {

        for (int i = 0; i < box.size(); i++)
        {
            for (int j = 0; j < box[0].size(); j++)
            {
                cout << box[i][j] << " ";
            }
            cout << endl;
        }

        return 1;
    }
    string word = words[idx];
    int count = 0;

    for (int i = 0; i < box.size(); i++)
    {
        for (int j = 0; j < box[0].size(); j++)
        {
            if (box[i][j] == '-' || box[i][j] == word[0])
            {
                //horizontal
                if (canPlaceHorizontal(box, word, i, j))
                {
                    vector<bool> placedLocation = placeHorizontal(box, word, i, j);
                    count += crossWordUtil(box, words, idx + 1);
                    unPlaceHorizontal(box, placedLocation, i, j);
                }

                //vertical
                if (canPlaceVertical(box, word, i, j))
                {
                    vector<bool> placedLocation = placeVertical(box, word, i, j);
                    count += crossWordUtil(box, words, idx + 1);
                    unPlaceVertical(box, placedLocation, i, j);
                }
            }
        }
    }
    return count;
}

void crossWord()
{

    vector<vector<char>> boards = {
        {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
        {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
        {'+', '-', '-', '-', '-', '-', '-', '-', '+', '+'},
        {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
        {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
        {'+', '-', '-', '-', '-', '-', '-', '+', '+', '+'},
        {'+', '-', '+', '+', '+', '-', '+', '+', '+', '+'},
        {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
        {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
        {'+', '+', '+', '+', '+', '+', '+', '+', '+', '+'}};

    vector<string> words = {"agra", "norway", "england", "gwalior"};
    cout << crossWordUtil(boards, words, 0) << endl;
}

void solve()
{
    // sudoku();
    // crypto();
    crossWord();
}

int main(int args, char **argv)
{
    solve();
    return 0;
}