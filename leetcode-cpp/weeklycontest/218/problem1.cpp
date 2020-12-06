#include <algorithm>
#include <deque>
#include <iostream>
#include <regex>
#include <stack>
#include <vector>

using namespace std;

class Solution {
public:
  string interpret(string command) {
    string res = regex_replace(command, regex("\\(\\)"), "o");
    res = regex_replace(res, regex("\\(al\\)"), "al");
    return res;
  }
};

int main() {
  Solution s;
  string command = "G()()()()(al)";
  cout << s.interpret(command) << endl;
}