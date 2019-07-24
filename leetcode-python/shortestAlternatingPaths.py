import math

class Solution(object):
    def shortestAlternatingPaths(self, n, red_edges, blue_edges):
        """
        :type n: int
        :type red_edges: List[List[int]]
        :type blue_edges: List[List[int]]
        :rtype: List[int]
        """
        node_nodelist_red = [[] for i in range(n)]
        node_nodelist_blue = [[] for i in range(n)]

        len1, len2 = len(red_edges), len(blue_edges)
        for i in range(len1):
            node_nodelist_red[red_edges[i][0]].append(red_edges[i][1])
        for i in range(len2):
            node_nodelist_blue[blue_edges[i][0]].append(blue_edges[i][1])
        
        dp_red = [math.inf for i in range(n)]
        dp_blue = [math.inf for i in range(n)]
        
        dp_red[0] = 0
        dp_blue[0] = 0

        red_used_edges = set()
        blue_used_edges = set()

        red_next_edges = [(0, i) for i in node_nodelist_red[0]]
        blue_next_edges = [(0, i) for i in node_nodelist_blue[0]]

        while len(red_next_edges) > 0 or len(blue_next_edges) > 0:
            t_red, t_blue = [], []
            for i, j in red_next_edges:
                dp_red[j] = min(dp_red[j], dp_blue[i] + 1)
                red_used_edges.add((i,j))
                t_blue.extend([(j, k) for k in node_nodelist_blue[j] if (j,k) not in blue_used_edges])
            
            for i, j in blue_next_edges:
                # 用过的边要删除
                dp_blue[j] = min(dp_blue[j], dp_red[i] + 1)
                blue_used_edges.add((i,j))
                t_red.extend([(j, k) for k in node_nodelist_red[j] if (j,k) not in red_used_edges])
            
            red_next_edges, blue_next_edges = t_red, t_blue
        
        dp = [min(i,j) for i, j in zip(dp_red, dp_blue)]
        dp = [i if i != math.inf else -1 for i in dp]

        return dp

s = Solution()
n = 5
red_edges = [[0,1],[1,2],[2,3],[3,4]]
blue_edges = [[1,2],[2,3],[3,1]]
print(s.shortestAlternatingPaths(n, red_edges, blue_edges))
