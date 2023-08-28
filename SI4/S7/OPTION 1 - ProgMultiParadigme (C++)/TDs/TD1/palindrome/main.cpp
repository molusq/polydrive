#include <iostream>
#include <string>

using std::cout;
using std::cin;
using std::string;
using std::endl;

bool test_palindrome(string *pt_pal) {
    if (pt_pal == NULL) {
        return false;
    }
    for (int i = 0; i < pt_pal -> length() / 2; i++) {
        if ((*pt_pal)[i] != (*pt_pal)[pt_pal -> length() - i - 1]) {
            return false;
        }
    }
    return true;
}

int main() {
    string palindrome;
    string test = "kayak";
    cout << "kayak est un palindrome : " << test_palindrome(&test) << endl;
    test = "otto";
    cout << "otto est un palindrome : " << test_palindrome(&test) << endl;

    cin >> palindrome;
    cout << palindrome << " est un palindrome : " << test_palindrome(&palindrome) << endl;

    return 0;
}