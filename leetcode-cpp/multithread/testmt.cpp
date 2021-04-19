#include <boost/lambda/lambda.hpp>
#include <boost/thread.hpp>
#include <vector>
#include <iostream>

using namespace std;

int help(vector<int> &vec) {
    vec.push_back(1);
    vec.push_back(2);

    return 0;
}

void threadFunc(int mark, std::vector<int> &vec) {
    vec.push_back(mark);
}

// LD_LIBRARY_PATH="/usr/local/lib";
// 运行前写这行，LD_LIBRARY_PATH设置在环境变量中没有生效
int main() {
    int threadnum = 5;
    boost::thread_group threads;
    std::vector<std::vector<int>> res_vec;
    for (int i = 0; i < threadnum; i++) {
        res_vec.push_back({});
    }
    
    // for (int i = 0; i < threadnum; i++) {
    //     // boost::ref 传引用进去
    //     threads.add_thread(new boost::thread(&threadFunc, i, boost::ref(res_vec[i])));
    // }
    // threads.join_all();


    std::vector<boost::shared_ptr<boost::thread>> thread_vec;
    for (int i = 0; i < threadnum; i++) {
      thread_vec.push_back(boost::shared_ptr<boost::thread>(new boost::thread(&threadFunc, i, boost::ref(res_vec[i]))));
    }
    for (int i = 0; i < threadnum; i++) {
      thread_vec[i]->join();
    }


    for (uint32_t i = 0; i < res_vec.size(); i++) {
        cout << res_vec[i][0] << endl;
    }

    return 0;
}