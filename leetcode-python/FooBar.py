import threading
import sys

def printFoo():
    print("foo")

def printBar():
    print("bar")


class FooBar(object):
    def __init__(self, n):
        self.n = n
        self.sema = threading.Semaphore(0)
        self.sema1 = threading.Semaphore(1)

    def foo(self, printFoo):
        """
        :type printFoo: method
        :rtype: void
        """
        for i in range(self.n):
            # printFoo() outputs "foo". Do not change or remove this line.
            self.sema1.acquire()
            printFoo()
            self.sema.release()


    def bar(self, printBar):
        """
        :type printBar: method
        :rtype: void
        """
        for i in range(self.n):
            self.sema.acquire()
            # printBar() outputs "bar". Do not change or remove this line.
            printBar()
            self.sema1.release()

    def run(self):
        thread1 = threading.Thread(target = self.foo, args=(printFoo,))
        thread2 = threading.Thread(target = self.bar, args=(printBar,))
        thread1.start()
        thread2.start()

foobar = FooBar(5)

foobar.run()



