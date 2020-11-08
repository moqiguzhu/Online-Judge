#include <algorithm>
#include <deque>
#include <iostream>
#include <set>
#include <stack>
#include <unordered_map>
#include <vector>

using namespace std;
bool compare(const pair<char, int> &left, const pair<char, int> &right) {
  return left.second > right.second;
}

class Solution {
public:
  // 有没有python中的counter
  int minDeletions(string s) {
    unordered_map<char, int> c_count;
    for (auto c : s) {
      c_count[c]++;
    }
    set<int> ufreq;
    vector<pair<char, int>> c_count_vec;
    for (auto kv : c_count) {
      ufreq.insert(kv.second);
      c_count_vec.push_back(make_pair(kv.first, kv.second));
    }
    sort(c_count_vec.begin(), c_count_vec.end(), compare);

    int last = -1;
    int res = 0;
    for (auto kv : c_count_vec) {
      if (last == kv.second) {
        int t = kv.second;
        do {
          t = t - 1;
          res += 1;
        } while (ufreq.count(t) == 1 and t > 0);
        ufreq.insert(t);
      }
      last = kv.second;
    }

    return res;
  }
};

int main() {
  Solution s;
  cout << s.minDeletions("bbcebab") << endl;
}