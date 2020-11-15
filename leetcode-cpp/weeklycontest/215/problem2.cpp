#include <algorithm>
#include <deque>
#include <iostream>
#include <stack>
#include <unordered_map>
#include <vector>

using namespace std;

class Solution {
public:
  bool closeStrings(string word1, string word2) {
    if (word1.empty() || word2.empty()) {
      if (word1.empty() && word2.empty()) {
        return true;
      }
      return false;
    }

    unordered_map<char, int> m1, m2;
    for (auto c : word1) {
      m1[c]++;
    }
    for (auto c : word2) {
      m2[c]++;
    }

    vector<int> freq1, freq2;
    for (auto kv : m1) {
      if (m2[kv.first] == 0) {
        return false;
      }
      freq1.push_back(kv.second);
    }
    for (auto kv : m2) {
      if (m1[kv.first] == 0) {
        return false;
      }
      freq2.push_back(kv.second);
    }

    sort(freq1.begin(), freq1.end());
    sort(freq2.begin(), freq2.end());

    for (int i = 0; i < freq1.size(); i++) {
      if (freq1[i] != freq2[i]) {
        return false;
      }
    }

    return true;
  }
};

int main() {
  Solution s;
  string word1 = "cabbba";
  string word2 = "aabbss";
  cout << s.closeStrings(word1, word2) << endl;
}