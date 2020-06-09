import numpy as np


def help1():
    res = []
    iter_steps = 0
    while len(res) < 255:
        t = set([np.random.randint(1, 16), np.random.randint(16, 31), np.random.randint(
            31, 46), np.random.randint(46, 61), np.random.randint(61, 76)])
        flag = True
        for e in res:
            if len(e.intersection(t)) > 1:
                flag = False
                break
        if flag:
            res.append(t)
        iter_steps += 1
        if iter_steps % 100000 == 0:
            print('迭代%s轮, 当前找到满足条件的图片数:%s' % (str(iter_steps), str(len(res))))

    print(res)


if __name__ == '__main__':
    help1()
