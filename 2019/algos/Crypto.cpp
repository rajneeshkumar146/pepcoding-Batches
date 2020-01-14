// To solve a crypt-arithmetic puzzel using recursion. Each letter represents a unique number between 0 and 9. The left-most alphabets can't be 0 as setting S or M = 0 would not let them of 4 digit numbers i.e. 0123 is 3 digit number not 4 digit.

/*     S E N D
    + M O R E
    ---------------
    M O N E Y 
 */

using namespace std;
#include <iostream>

string s1 = "send", s2 = "more", s3 = "money";

int string_value(string str1, int *map)
{
    int res = 0;
    for (int i = 0; i < str1.length(); i++)
    {
        res = res * 10 + map[str1[i] - 'a'];
    }
    return res;
}

// Function to take input all the unique alphabets in combined string as input and gives all possible combinations which satisfy the equation
int solveCrypto(string str1, int bit_num, int *mapping)
{
    if (str1.length() == 0) // When all letters of the combined input string has been computed upon...
    {
        if (mapping[s1[0] - 'a'] != 0 && mapping[s2[0] - 'a'] != 0 && mapping[s3[0] - 'a'] != 0)
        {
            if (string_value(s1, mapping) + string_value(s2, mapping) == string_value(s3, mapping))
            {
                for (int i = 0; i < 26; i++)
                {
                    if (mapping[i] != -1)
                    {
                        cout << (char)(i + 'A') << '=' << mapping[i] << "  ";
                    }
                }
                cout << endl;
                return 1;
            }
        }
        return 0;
    }

    int count = 0;
    for (int num = 0; num <= 9; num++)
    {
        if (!(bit_num & (1 << num)))
        {
            mapping[str1[0] - 'a'] = num;
            bit_num ^= (1 << num);

            count += solveCrypto(str1.substr(1), bit_num, mapping); // maps str[0] to num and send all the remaining string, i.e. str.substr(0) for next calculations.

            mapping[str1[0] - 'a'] = -1;
            bit_num ^= (1 << num);
        }
    }
    return count;
}

int main()
{

    int arr[26] = {0};
    string s4 = s1 + s2 + s3;

    // find and remove duplicate characters
    for (int i = 0; i < s4.length(); i++)
    {
        arr[s4[i] - 'a']++; // incrementing associated index of an alphabet. E.g for 'c', index 2 will be incremented, as 'a' starts on 0 index.
    }

    string s5 = "";
    for (int i = 0; i < 26; i++)
    {
        if (arr[i] != 0)
            s5 += (char)(i + 'a');
    }
    if (s5.length() > 10)
    {
        cout << "No Combination possible. Combined unique elements more than 10";
        return 0;
    }

    int map[26];

    //    setting the mapping array to default value of -1
    for (int i = 0; i < 26; i++)
        map[i] = -1;

    cout << solveCrypto(s5, 0, map);

    return 0;
}