#include <algorithm>
#include <deque>
#include <iostream>
#include <stack>
#include <vector>

using namespace std;

class Solution {
public:
  bool arrayStringsAreEqual(vector<string> &word1, vector<string> &word2) {
    string s1(""), s2("");
    for (auto e : word1) {
      s1 += e;
    }
    for (auto e : word2) {
      s2 += e;
    }

    return s1 == s2;
  }
};