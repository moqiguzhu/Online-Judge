
/**
 * 递归实现，实际上还是一个DFS
 * 
 * @author moqiguzhu
 * @date 2016-03-14
 * @version 1.0
 *
 */
public class HouseRobberIII {
	public int rob(TreeNode root) {
        if(root == null) {
        	return 0;
        }
        if(root.left == null && root.right == null) {
        	return root.val;
        }
        
        int left = rob(root.left);
        int right = rob(root.right);
        
        int ans1 = left + right;
        int ans2 = root.val;
        if(left != 0) ans2 = ans2 + rob(root.left.left) + rob(root.left.right);
        if(right != 0) ans2 = ans2 + rob(root.right.left) + rob(root.right.right);
        
        return ans1 > ans2 ? ans1 : ans2;
    }
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.right = new TreeNode(1);
		root.right.right = new TreeNode(3);
		
		HouseRobberIII hr = new HouseRobberIII();
		
		System.out.println(hr.rob(root));
	}
	
	
}
