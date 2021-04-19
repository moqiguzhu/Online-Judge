// 这段代码不能编译过
// 多线程并行计算的一种写法
int main() {
  boost::thread_group threads;
  for (int i = 0; i < threadnum; i++) {
    threads.add_thread(new boost::thread(&FivekvThreadFunction, uin_vec[i], datatype, i, res_vec[i]));
  }
  threads.join_all();

  // std::vector<boost::shared_ptr<boost::thread>> thread_vec;
  // for (int i = 0; i < threadnum; i++) {
  //   thread_vec.push_back(boost::shared_ptr<boost::thread>(new boost::thread(&FivekvThreadFunction, uin_vec[i], datatype, i, res_vec[i])));
  // }
  
  // for (int i = 0; i < threadnum; i++) {
  //   thread_vec[i]->join();
  // }
}