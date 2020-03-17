from collections import defaultdict, deque, Counter
from typing import List
import math
import copy
import random
import numpy as np
import bisect

import inspect

import heapq


class CustomStack:

    def __init__(self, maxSize: int):
        self.stack = []
        self.inc = []
        self.maxSize = maxSize

    def push(self, x: int) -> None:
        if len(self.stack) < self.maxSize:
            self.stack.append(x)
            self.inc.append(0)
        return None

    def pop(self) -> int:
        if len(self.stack) > 0:
            return self.stack.pop(-1) + self.inc.pop(-1)
        else:
            return -1

    def increment(self, k: int, val: int) -> None:
        for i in range(min(k, len(self.inc))):
            self.inc[i] += val


if __name__ == '__main__':
    customStack = CustomStack(3)  # Stack is Empty []
    customStack.push(1)                          # stack becomes [1]
    customStack.push(2)                          # stack becomes [1, 2]
    # return 2 --> Return top of the stack 2, stack becomes [1]
    customStack.pop()
    customStack.push(2)                          # stack becomes [1, 2]
    customStack.push(3)                          # stack becomes [1, 2, 3]
    # stack still [1, 2, 3], Don't add another elements as size is 4
    customStack.push(4)
    # stack becomes [101, 102, 103]
    customStack.increment(5, 100)
    # stack becomes [201, 202, 103]
    customStack.increment(2, 100)
    # return 103 --> Return top of the stack 103, stack becomes [201, 202]
    print(customStack.pop())
    # return 202 --> Return top of the stack 102, stack becomes [201]
    print(customStack.pop())
    # return 201 --> Return top of the stack 101, stack becomes []
    customStack.pop()
    # return -1 --> Stack is empty return -1.
    print(customStack.pop())
