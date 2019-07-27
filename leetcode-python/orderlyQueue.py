from PriorityHeap import PriorityHeap

# 这个证明有点厉害
#Actually, when K>=2, we can prove that we can use the first 2 elements as a buffer to swap any two adjacent characters. Since we can reach any permutation by swapping adjacent characters (like bubble sort), in this case the minimal reachable permutation is the sorted S.

#Assume that we want to swap S[i] and S[i+1], we can first pop first i-1 characters to the end, then pop i+1 and i, finally pop i+2~end.

# bubble sort
class Solution(object):
    def orderlyQueue1(self, S, K):
        """
        :type S: str
        :type K: int
        :rtype: str
        """
        if K > len(S) / 2:
            t = [e for e in S]
            t = sorted(t)
            return ''.join(t)
        
        heap = PriorityHeap(S[0:K], lambda x: x)
        tmp  = S
        for i in range(K,len(S)-1,1):
            t = heap.pop()
            tmp = tmp + t
            heap.push(S[i])
        while heap.length() > 0:
            t = heap.pop()
            tmp = tmp + t
        res = ''
        print(tmp)
        for i in range(len(S)):
            if res == '':
                res = tmp[i:i+len(S)]
            else:
                res = min(res, tmp[i:i+len(S)])
        return res
    def orderlyQueue(self, S, K):
        return "".join(sorted(S)) if K > 1 else min(S[i:] + S[:i] for i in range(len(S)))

s = Solution()
S = "mslht"
K = 4
print(s.orderlyQueue(S, K))