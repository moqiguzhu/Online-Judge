package Leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

class MedianFinder {
  // low half是个大顶堆
  
  // 兼容JDK7的写法
  PriorityQueue<Integer> lowHalf = new PriorityQueue<>(0, Collections.reverseOrder());
  // high half是个小顶堆
  PriorityQueue<Integer> highHalf = new PriorityQueue<>();

  // Adds a number into the data structure.
  public void addNum(int num) {
    // 往堆里加元素
    if (lowHalf.isEmpty() || lowHalf.peek() >= num) {
      lowHalf.offer(num);
    } else {
      highHalf.offer(num);
    }

    // 加元素之后，可能出现不均衡的情况，处理不均衡
    if (lowHalf.size() - highHalf.size() > 1) {
      highHalf.offer(lowHalf.poll());
    }
    if (highHalf.size() - lowHalf.size() > 1) {
      lowHalf.offer(highHalf.poll());
    }
  }

  // Returns the median of current data stream
  public double findMedian() {
    double curMed = 0;
    if (lowHalf.isEmpty() || highHalf.size() > lowHalf.size()) {
      curMed = highHalf.peek();
    } else if (highHalf.isEmpty() || lowHalf.size() > highHalf.size()) {
      curMed = lowHalf.peek();
    } else {
      curMed = (lowHalf.peek() + highHalf.peek()) / 2.0; // 偶数个元素
    }

    return curMed;
  }
  
  public static void main(String[] args) {
    MedianFinder mf = new MedianFinder();
    mf.addNum(1);
    System.out.println(mf.findMedian());
    
    mf.addNum(2);
    System.out.println(mf.findMedian());
    
    mf.addNum(3);
    System.out.println(mf.findMedian());
  }
}
