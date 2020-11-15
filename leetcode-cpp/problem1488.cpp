#include <algorithm>
#include <deque>
#include <iostream>
#include <stack>
#include <unordered_map>
#include <vector>
using namespace std;

// 算法有问题
// 0 1 1 不是所有的0都可以用来抵消, 有先后依赖关系
class Solution {
public:
  vector<int> avoidFlood(vector<int> &rains) {
    vector<int> res;
    if (rains.empty() || rains.empty()) {
      return rains;
    }

    if (rains.size() == 1) {
      if (rains[0] > 0) {
        res.push_back(-1);
        return res;
      } else {
        res.push_back(1);
        return res;
      }
    }

    unordered_map<int, int> lake_cnt;
    vector<int> t;
    int i = rains.size(), j = 0, lake;
    for (j = 0; j < rains.size(); j++) {
      lake = rains[j];
      if (lake > 0) {
        if (lake_cnt[lake] == 0) {
          lake_cnt[lake]++;
        } else { // == 1
          if (i > j) {
            return res;
          }
          t.push_back(lake);
          for (int k = i + 1; k < rains.size(); k++) {
            if (rains[k] == 0) {
              i = k;
              break;
            }
            if (k == rains.size() - 1) {
              i = rains.size();
            }
          }
        }
      } else {
        if (i == rains.size()) {
          i = j;
        }
      }
    }

    auto it = t.begin();
    for (auto lake : rains) {
      if (lake == 0) {
        if (it == t.end()) {
          res.push_back(1);
        } else {
          res.push_back(*it);
          it++;
        }
      } else {
        res.push_back(-1);
      }
    }

    return res;
  }
};

int main() {
  Solution s;
  vector<int> rains = {0, 1, 1};

  vector<int> res = s.avoidFlood(rains);
  for (auto e : res) {
    cout << e << " ";
  }
  return 0;
}