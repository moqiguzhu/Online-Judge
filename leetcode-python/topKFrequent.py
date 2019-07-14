from random import randint

class Solution(object):
    def topKFrequent(self, nums, k):
        num_cnt = {}
        for i in nums:
            if i not in num_cnt:
                num_cnt[i] = 1
            else:
                num_cnt[i] = num_cnt[i] + 1

        k_cnt = self.findKthMax(list(num_cnt.values()), k)

        if k_cnt is None:
            return []
        else:
            t = []
            for num, cnt in num_cnt.items():
                if cnt >= k_cnt:
                    t.append(num)
            return t


    
    def findKthMax(self, l,k):
        if k>len(l):
            return None
        #随机生成一个下标key,并获取下标对应的数组值keyv
        key=randint(0,len(l)-1)
        keyv=l[key]
        
        #遍历数组（刨除key），sl数组是小于keyv的值，bl数组是大于等于keyv的值
        sl = [i for i in l[:key] + l[key + 1:] if i < keyv]
        bl = [i for i in l[:key] + l[key + 1:] if i >= keyv]

        #如果bl的长度恰好是k-1,那么说明keyv就是第k大的数
        if len(bl)==k-1:
            return keyv
        #如果bl的长度大于等于k,说明第k大的数在bl中，迭代findKthMax函数，找出bl中第k大的数
        elif len(bl)>=k:
            return self.findKthMax(bl,k)
        #如果bl的长度小于k-1,说明第k大的数在sl中，因为bl中已经有len(bl)个比目标值大的数，加上keyv本身，所以要找出sl中第（k-len(bl)-1）大的数
        else:
            return self.findKthMax(sl,k-len(bl)-1)


s = Solution()
nums = [1,1,1,2,2,3]
k = 2

print(s.topKFrequent(nums, k))
