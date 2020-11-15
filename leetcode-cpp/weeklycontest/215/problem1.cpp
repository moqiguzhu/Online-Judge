#include <algorithm>
#include <deque>
#include <iostream>
#include <stack>
#include <vector>

using namespace std;

class OrderedStream {
public:
  vector<string> data;
  int ptr;
  OrderedStream(int n) {
    data.resize(n + 1);
    ptr = 1;
  }

  vector<string> insert(int id, string value) {
    vector<string> res;
    data[id] = value;

    if (id == ptr) {
      int i;
      for (i = id; i < data.size(); i++) {
        if (data[i].empty()) {
          break;
        } else {
          res.push_back(data[i]);
        }
      }
      ptr = i;
    }
    return res;
  }
};

/**
 * Your OrderedStream object will be instantiated and called as such:
 * OrderedStream* obj = new OrderedStream(n);
 * vector<string> param_1 = obj->insert(id,value);
 */