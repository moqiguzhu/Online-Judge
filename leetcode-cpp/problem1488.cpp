#include <algorithm>
#include <deque>
#include <iostream>
#include <stack>
#include <unordered_map>
#include <vector>
using namespace std;

class Solution {
public:
  vector<int> avoidFlood(vector<int> &rains) {
    vector<int> res;
    if (rains == NULL || rains.empty()) {
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
    int i = 0, j = 0;
    for (auto lake : rains) {
      if (lake > 0) {
        if (lake_cnt[lake] == 0) {
          lake_cnt[lake]++;
        } else { // == 1
          if (i < j) {
            t.push_back(lake);
            for (int k = i + 1; k < rains.size(); k++) {
              if (rains[k] == 0) {
                i = k;
                break;
              }
            }
          }
        }
        i++;
      } else {
        continue;
      }
      j++;
    }
  }
};