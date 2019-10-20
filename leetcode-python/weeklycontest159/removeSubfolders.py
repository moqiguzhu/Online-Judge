from typing import List


class Solution:
    def removeSubfolders(self, folder: List[str]) -> List[str]:
        sorted_folder = sorted(folder)
        res_set = set()
        for folder in sorted_folder:
            paths = folder.split("/")[1:]
            cur_path = ''
            flag = True
            for path in paths:
                cur_path = cur_path + '/' + path
                if cur_path in res_set:
                    flag = False
                    break
            if flag:
                res_set.add(folder)

        return list(res_set)


s = Solution()
folder = ["/a", "/a/b", "/c/d", "/c/d/e", "/c/f"]
print(s.removeSubfolders(folder))
